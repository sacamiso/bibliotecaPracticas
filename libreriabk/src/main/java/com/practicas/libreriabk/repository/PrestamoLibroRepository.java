package com.practicas.libreriabk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntityID;

public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibroEntity, PrestamoLibroEntityID>{
	
	@Query("SELECT pl.libro FROM PrestamoLibroEntity pl WHERE pl.idPrestamo = :idPrestamo")
	List<LibroEntity> getLibrosFromPrestamo(@Param("idPrestamo") int idPrestamo);
	
	@Query("SELECT pl.prestamo FROM PrestamoLibroEntity pl WHERE pl.idLibro = :idLibro")
	List<PrestamoEntity> getPrestamosFromLibro(@Param("idLibro") int idLibro);
}
