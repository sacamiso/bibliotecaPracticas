package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.PrestamoEntity;

public interface PrestamoProvider {
	
	List<PrestamoDto> listarPrestamos();
	PrestamoDto anadirPrestamo(PrestamoDto prestaDt);
	PrestamoDto buscarPrestamoId(int prestamoId);
	PrestamoDto editarPrestamo(PrestamoDto prestamo, int prestamoId);
	void deletePrestamoById(int prestamoId);
	List<LibroDto> listarLibrosPrestamo(int prestamoId);
	PrestamoDto convertToDtoPrestamo(PrestamoEntity prestamoE);
	PrestamoEntity convertToEntityPrestamo(PrestamoDto presDto);
}
