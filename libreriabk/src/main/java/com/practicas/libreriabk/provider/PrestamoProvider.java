package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;

public interface PrestamoProvider {
	
	List<PrestamoEntity> listarPrestamos();
    PrestamoEntity anadirPrestamo(PrestamoEntity prestamo);
	PrestamoEntity buscarPrestamoId(int prestamoId);
	PrestamoEntity editarPrestamo(PrestamoEntity prestamo, int prestamoId);
	void deletePrestamoById(int prestamoId);
	List<LibroEntity> listarLibrosPrestamo(int prestamoId);
	PrestamoDto convertToDtoPrestamo(PrestamoEntity prestamoE);
	PrestamoEntity convertToEntityPrestamo(PrestamoDto presDto);
}
