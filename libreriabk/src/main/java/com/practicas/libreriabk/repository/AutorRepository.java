package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practicas.libreriabk.entity.AutorEntity;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Integer>{

	@Query("SELECT a FROM AutorEntity a WHERE a.dni = :dni")
	AutorEntity getAutorFromDni(@Param("dni") String dni);
	
}
