package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.LibroEntity;

public interface LibroProvider {
	
	List<LibroDto> listarLibros();
	LibroDto anadirLibro(LibroDto libro);
	LibroDto buscarLibroId(int libroId);
	LibroDto editarLibro(LibroDto libro, int libroId);
	void deleteLibroById(int libroId);
	List<PrestamoDto> listarPrestamosLibro(int libroId);
	LibroDto convertToDtoLibro(LibroEntity liE);
	LibroEntity convertToEntityLibro(LibroDto libroDto);
}
