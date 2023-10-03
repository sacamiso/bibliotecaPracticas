package com.practicas.providerImpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.dto.LibroDto;
import com.practicas.entity.AutorEntity;
import com.practicas.entity.CategoriaEntity;
import com.practicas.entity.LibroEntity;
import com.practicas.entity.PrestamoEntity;
import com.practicas.provider.LibroProvider;
import com.practicas.provider.PrestamoLibroProvider;
import com.practicas.repository.AutorRepository;
import com.practicas.repository.CategoriaRepository;
import com.practicas.repository.LibroRepository;

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
	public List<LibroEntity> listarLibros() {
		return libroRepository.findAll();
	}

	@Override
	public LibroEntity anadirLibro(LibroEntity libro) {
		AutorEntity autor = autorRepository.getReferenceById(libro.getIdAutor());
		if (autor == null) {
			return null;
		}

		CategoriaEntity categoria = categoriaRepository.getReferenceById(libro.getIdCategoria());
		if (categoria == null) {
			return null;
		}
		return libroRepository.save(libro);
	}

	@Override
	public LibroEntity buscarLibroId(int libroId) {
		if(!libroRepository.findById(libroId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return libroRepository.getReferenceById(libroId);
	}

	@Override
	public LibroEntity editarLibro(LibroEntity libro, int libroId) {
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

		return libroRepository.save(libroDB);
	}

	@Override
	public void deleteLibroById(int libroId) {
		libroRepository.deleteById(libroId);
	}

	@Override
	public List<PrestamoEntity> listarPrestamosLibro(int libroId) {
		if(!libroRepository.findById(libroId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
        return plProvider.buscarPrestamosPorLibroId(libroId);
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

}
