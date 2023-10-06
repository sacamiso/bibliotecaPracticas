package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntity;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.provider.PrestamoLibroProvider;
import com.practicas.libreriabk.repository.AutorRepository;
import com.practicas.libreriabk.repository.CategoriaRepository;
import com.practicas.libreriabk.repository.LibroRepository;

@Service
public class LibroProviderImpl implements LibroProvider {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private PrestamoLibroProvider plProvider;

	@Override
	public List<LibroDto> listarLibros() {
		List<LibroEntity> listaLibrosEnt = libroRepository.findAll();
		return listaLibrosEnt.stream().map(this::convertToDtoLibro).collect(Collectors.toList());
	}

	@Override
	public LibroDto anadirLibro(LibroDto libro) {
		
		if (!autorRepository.findById(libro.getIdAutor()).isPresent()) {
			return null;
		}

		if (!categoriaRepository.findById(libro.getIdCategoria()).isPresent()) {
			return null;
		}
		return this.convertToDtoLibro(libroRepository.save(this.convertToEntityLibro(libro)));
	}

	@Override
	public LibroDto buscarLibroId(int libroId) {
		if(!libroRepository.findById(libroId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return this.convertToDtoLibro(libroRepository.getReferenceById(libroId));
	}

	@Override
	public LibroDto editarLibro(LibroDto libro, int libroId) {
		if(!libroRepository.findById(libroId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		LibroEntity libroDB = libroRepository.getReferenceById(libroId);

		if (Objects.nonNull(libro.getTitulo()) && !"".equalsIgnoreCase(libro.getTitulo())) {
			libroDB.setTitulo(libro.getTitulo());
		}
		if (Objects.nonNull(libro.getEdicion())) {
			libroDB.setEdicion(libro.getEdicion());
		}

		return this.convertToDtoLibro(libroRepository.save(libroDB));
	}

	@Override
	public void deleteLibroById(int libroId) {
		libroRepository.deleteById(libroId);
	}

	@Override
	public List<PrestamoDto> listarPrestamosLibro(int libroId) {
		if(!libroRepository.findById(libroId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		List<PrestamoEntity> listaPrestamoEnt = plProvider.buscarPrestamosPorLibroId(libroId);
        return listaPrestamoEnt.stream().map(this::convertToDtoPrestamo).collect(Collectors.toList());
	}

	@Override
	public LibroDto convertToDtoLibro(LibroEntity liE) {
		return modelMapper.map(liE, LibroDto.class);
	}

	@Override
	public LibroEntity convertToEntityLibro(LibroDto libroDto) {
		LibroEntity libroEntity = modelMapper.map(libroDto, LibroEntity.class);
	    return libroEntity;
	}
	
	private PrestamoDto convertToDtoPrestamo(PrestamoEntity prestamoE) {
		PrestamoDto presDto = modelMapper.map(prestamoE, PrestamoDto.class);

		List<LibroDto> libros = this.mapPrestamoLibroEntityListToLibroDtoList(prestamoE.getLibros());

		presDto.setLibros(libros);

		return presDto;
	}
	
	private List<LibroDto> mapPrestamoLibroEntityListToLibroDtoList(List<PrestamoLibroEntity> prestamosLibroEntity) {
		return prestamosLibroEntity.stream().map(plEntity -> mapPrestamoLibroEntityToLibroDto(plEntity))
				.collect(Collectors.toList());
	}
	
	private LibroDto mapPrestamoLibroEntityToLibroDto(PrestamoLibroEntity prestamoLibroEntity) {
		LibroEntity libroEntity = prestamoLibroEntity.getLibro();
		return modelMapper.map(libroEntity, LibroDto.class);
	}

}
