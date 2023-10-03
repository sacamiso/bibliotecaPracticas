package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.AutorEntity;
import com.practicas.libreriabk.entity.LibroEntity;

public interface AutorProvider {
	
	List<AutorEntity> listarAutores();
    AutorEntity anadirAutor(AutorEntity autor);
	AutorEntity buscarAutorId(int autorId);
	AutorEntity editarAutor(AutorEntity autor, int autorId);
	void deleteAutorById(int autorId);
	List<LibroEntity> listarLibrosAutor(int autorId);
	AutorDto convertToDtoAutor(AutorEntity auE);
	AutorEntity convertToEntityAutor(AutorDto auDto);
}
