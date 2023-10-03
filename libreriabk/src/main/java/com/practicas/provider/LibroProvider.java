package com.practicas.provider;

import java.util.List;

import com.practicas.dto.LibroDto;
import com.practicas.entity.LibroEntity;
import com.practicas.entity.PrestamoEntity;

public interface LibroProvider {
	
	List<LibroEntity> listarLibros();
    LibroEntity anadirLibro(LibroEntity libro);
	LibroEntity buscarLibroId(int libroId);
	LibroEntity editarLibro(LibroEntity libro, int libroId);
	void deleteLibroById(int libroId);
	List<PrestamoEntity> listarPrestamosLibro(int libroId);
	LibroDto convertToDtoLibro(LibroEntity liE);
	LibroEntity convertToEntityLibro(LibroDto libroDto);
}
