package provider;

import java.util.List;

import entity.LibroEntity;
import entity.PrestamoEntity;
import entity.PrestamoLibroEntity;


public interface PrestamoLibroProvider {
	
	List<PrestamoLibroEntity> listarPrestamosLibros();
	PrestamoLibroEntity anadirLibro(PrestamoLibroEntity prestamoLibro);
	PrestamoLibroEntity buscarPrestamoLibroId(int libroId, int prestamoId);
	void deletePrestamoLibroById(int libroId, int prestamoId);
	List<PrestamoEntity> buscarPrestamosPorLibroId(int libroId);
	List<LibroEntity> buscarLibrosPorPrestamoId(int prestamoId);

}
