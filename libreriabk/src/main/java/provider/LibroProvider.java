package provider;

import java.util.List;

import entity.LibroEntity;
import entity.PrestamoEntity;

public interface LibroProvider {
	
	List<LibroEntity> listarLibros();
    LibroEntity anadirLibro(LibroEntity libro);
	LibroEntity buscarLibroId(int libroId);
	LibroEntity editarLibro(LibroEntity libro, int libroId);
	void deleteLibroById(int libroId);
	List<PrestamoEntity> listarPrestamosLibro(int libroId);
}
