package com.practicas.libreriabk.providerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntity;
import com.practicas.libreriabk.entity.UsuarioEntity;
import com.practicas.libreriabk.provider.PrestamoLibroProvider;
import com.practicas.libreriabk.provider.PrestamoProvider;
import com.practicas.libreriabk.repository.PrestamoLibroRepository;
import com.practicas.libreriabk.repository.PrestamoRepository;
import com.practicas.libreriabk.repository.UsuarioRepository;

@Service
public class PrestamoProviderImpl implements PrestamoProvider {

	private ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrestamoLibroRepository prestamoLibroRepository;

	@Autowired
	private PrestamoLibroProvider plProvider;

	@Override
	public List<PrestamoEntity> listarPrestamos() {
		return prestamoRepository.findAll();
	}

	@Override
	public PrestamoEntity anadirPrestamo(PrestamoEntity prestamo, PrestamoDto presDto) {
		Optional<UsuarioEntity> usuarioOpt = this.usuarioRepository.findById(prestamo.getIdUsuario());
		if (!usuarioOpt.isPresent()) {
			return null;
		}
		
		List<PrestamoLibroEntity> prestamoLibros = new ArrayList<PrestamoLibroEntity>();
		
		prestamo.setLibros(prestamoLibros);
		PrestamoEntity aux = this.prestamoRepository.save(prestamo);

		// Mapea la lista de libros de LibroDto a PrestamoLibroEntity y establece la
		// relación con PrestamoEntity
		prestamoLibros = this.mapLibrosDtoToPrestamoLibroEntityList(presDto.getLibros(),
				aux);

		// Establece la lista de PrestamoLibroEntity en PrestamoEntity
		aux.setLibros(prestamoLibros);
		
		this.prestamoLibroRepository.saveAll(prestamoLibros);
		return aux;
	}

	@Override
	public PrestamoEntity buscarPrestamoId(int prestamoId) {

		if (!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; // no me gusta esto hay que aponerlo mejor
		}

		return this.prestamoRepository.getReferenceById(prestamoId);
	}

	@Override
	public PrestamoEntity editarPrestamo(PrestamoEntity prestamo, int prestamoId) {

		if (!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; // no me gusta esto hay que aponerlo mejor
		}

		PrestamoEntity prestamoDB = prestamoRepository.getReferenceById(prestamoId);

		prestamoDB.setDevolucion(prestamo.getDevolucion());

		if (Objects.nonNull(prestamo.getPrestamo())) {
			prestamoDB.setPrestamo(prestamo.getPrestamo());
		}

		return prestamoRepository.save(prestamoDB);
	}

	@Override
	public void deletePrestamoById(int prestamoId) {
		this.prestamoRepository.deleteById(prestamoId);

	}

	@Override
	public List<LibroEntity> listarLibrosPrestamo(int prestamoId) {
		if (!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; // no me gusta esto hay que aponerlo mejor
		}
		return plProvider.buscarLibrosPorPrestamoId(prestamoId);
	}

	@Override
	public PrestamoDto convertToDtoPrestamo(PrestamoEntity prestamoE) {
		PrestamoDto presDto = modelMapper.map(prestamoE, PrestamoDto.class);

		List<LibroDto> libros = this.mapPrestamoLibroEntityListToLibroDtoList(prestamoE.getLibros());

		presDto.setLibros(libros);

		return presDto;
	}

	// Metodo auxiliar para realizar el mapeo de prestamoLibroEntity a LibroDto
	private LibroDto mapPrestamoLibroEntityToLibroDto(PrestamoLibroEntity prestamoLibroEntity) {
		LibroEntity libroEntity = prestamoLibroEntity.getLibro();
		return modelMapper.map(libroEntity, LibroDto.class);
	}

	// Se encarga de tratar la lista
	private List<LibroDto> mapPrestamoLibroEntityListToLibroDtoList(List<PrestamoLibroEntity> prestamosLibroEntity) {
		return prestamosLibroEntity.stream().map(plEntity -> mapPrestamoLibroEntityToLibroDto(plEntity))
				.collect(Collectors.toList());
	}

	@Override
	public PrestamoEntity convertToEntityPrestamo(PrestamoDto presDto) {
		PrestamoEntity prestamoEntity = modelMapper.map(presDto, PrestamoEntity.class);
		return prestamoEntity;
	}

	// he creado este método auxiliar para poder realizar el mapeo de LibroDto a
	// PrestamoLibroEntity ya que automático no se puede porque los campos son muy
	// diferentes
	private PrestamoLibroEntity mapLibroDtoToPrestamoLibroEntity(LibroDto libroDto, PrestamoEntity prestamoEntity) {
		PrestamoLibroEntity prestamoLibroEntity = new PrestamoLibroEntity();
		prestamoLibroEntity.setIdPrestamo(prestamoEntity.getIdPrestamo());
		prestamoLibroEntity.setIdLibro(libroDto.getIdLibro());
		return prestamoLibroEntity;
	}

	// Se encarga de tratar la lista
	private List<PrestamoLibroEntity> mapLibrosDtoToPrestamoLibroEntityList(List<LibroDto> librosDto,
			PrestamoEntity prestamoEntity) {
		return librosDto.stream().map(libroDto -> mapLibroDtoToPrestamoLibroEntity(libroDto, prestamoEntity))
				.collect(Collectors.toList());
	}

}
