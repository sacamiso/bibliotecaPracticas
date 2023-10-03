package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;

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
