package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practicas.libreriabk.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer>{
	@Query("SELECT c FROM CategoriaEntity c WHERE c.nombre = :nombre")
	CategoriaEntity getCategoriaFromNombre(@Param("nombre") String nombre);
}
