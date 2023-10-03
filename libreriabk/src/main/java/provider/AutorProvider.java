package provider;

import java.util.List;

import dto.AutorDto;
import dto.LibroDto;
import entity.AutorEntity;
import entity.LibroEntity;

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
