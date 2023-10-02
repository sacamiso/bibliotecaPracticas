package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import entity.PrestamoEntity;
import entity.PrestamoLibroEntity;
import entity.PrestamoLibroEntityID;

public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibroEntity, PrestamoLibroEntityID>{
	
	@Query("SELECT pl.libro FROM PrestamoLibroEntity pl WHERE pl.idPrestamo = :idPrestamo")
	List<PrestamoEntity> getLibrosFromPrestamo(@Param("idPrestamo") int idPrestamo);
	
	@Query("SELECT pl.prestamo FROM PrestamoLibroEntity pl WHERE pl.idLibro = :idLibro")
	List<PrestamoEntity> getPrestamosFromLibro(@Param("idLibro") int idLibro);
}
