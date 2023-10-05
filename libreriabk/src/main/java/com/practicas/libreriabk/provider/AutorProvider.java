package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.AutorEntity;

public interface AutorProvider {
	
	List<AutorDto> listarAutores();
	AutorDto anadirAutor(AutorDto autor);
	AutorDto buscarAutorId(int autorId);
	AutorDto editarAutor(AutorDto autor, int autorId);
	void deleteAutorById(int autorId);
	List<LibroDto> listarLibrosAutor(int autorId);
	AutorDto convertToDtoAutor(AutorEntity auE);
	AutorEntity convertToEntityAutor(AutorDto auDto);
}
