package com.practicas.providerImpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.dto.PrestamoDto;
import com.practicas.entity.LibroEntity;
import com.practicas.entity.PrestamoEntity;
import com.practicas.entity.UsuarioEntity;
import com.practicas.provider.PrestamoLibroProvider;
import com.practicas.provider.PrestamoProvider;
import com.practicas.provider.UsuarioProvider;
import com.practicas.repository.PrestamoRepository;

@Service
public class PrestamoProviderImpl implements PrestamoProvider {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private PrestamoRepository prestamoRepository;

	@Autowired
	private UsuarioProvider usuarioProvider;

	@Autowired
	private PrestamoLibroProvider plProvider;

	@Override
	public List<PrestamoEntity> listarPrestamos() {
		return prestamoRepository.findAll();
	}

	@Override
	public PrestamoEntity anadirPrestamo(PrestamoEntity prestamo) {

		UsuarioEntity usuAux = usuarioProvider.buscarUsuarioId(prestamo.getId());

		if (Objects.isNull(usuAux)) {
			return null;
		}
		
		return this.prestamoRepository.save(prestamo);
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

		prestamoDB.setFechaDevolucion(prestamo.getFechaDevolucion());

		if (Objects.nonNull(prestamo.getFechaPrestamo())) {
			prestamoDB.setFechaPrestamo(prestamo.getFechaPrestamo());
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
	    return presDto;
	}

	@Override
	public PrestamoEntity convertToEntityPrestamo(PrestamoDto presDto) {
		return modelMapper.map(presDto, PrestamoEntity.class);
	}

}
