package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.PrestamoLibroDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntity;


public interface PrestamoLibroProvider {
	
	List<PrestamoLibroEntity> listarPrestamosLibros();
	PrestamoLibroEntity anadirLibro(PrestamoLibroEntity prestamoLibro);
	PrestamoLibroEntity buscarPrestamoLibroId(int libroId, int prestamoId);
	void deletePrestamoLibroById(int libroId, int prestamoId);
	List<PrestamoEntity> buscarPrestamosPorLibroId(int libroId);
	List<LibroEntity> buscarLibrosPorPrestamoId(int prestamoId);
	PrestamoLibroDto convertToDtoPrestamoLibro(PrestamoLibroEntity plE);
	PrestamoLibroEntity convertToEntityPrestamoLibro(PrestamoLibroDto plDto);

}
