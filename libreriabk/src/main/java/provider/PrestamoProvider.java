package provider;

import java.util.List;

import entity.LibroEntity;
import entity.PrestamoEntity;

public interface PrestamoProvider {
	
	List<PrestamoEntity> listarPrestamos();
    PrestamoEntity anadirPrestamo(PrestamoEntity prestamo);
	PrestamoEntity buscarPrestamoId(int prestamoId);
	PrestamoEntity editarPrestamo(PrestamoEntity prestamo, int prestamoId);
	void deletePrestamoById(int prestamoId);
	List<LibroEntity> listarLibrosPrestamo(int prestamoId);
}
