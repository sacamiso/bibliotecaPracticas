package provider;

import java.util.List;

import entity.PrestamoLibroEntity;


public interface PrestamoLibroProvider {
	
	List<PrestamoLibroEntity> listarPrestamosLibros();
	PrestamoLibroEntity anadirLibro(PrestamoLibroEntity prestamoLibro);
	PrestamoLibroEntity buscarPrestamoLibroId(int libroId, int prestamoId);
	PrestamoLibroEntity editarPrestamoLibro(PrestamoLibroEntity prestamoLibro, int libroId, int prestamoId);
	void deletePrestamoLibroById(int libroId, int prestamoId);

}
