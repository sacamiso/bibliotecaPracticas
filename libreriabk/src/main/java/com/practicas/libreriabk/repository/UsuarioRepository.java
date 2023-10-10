package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practicas.libreriabk.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	
	@Query("SELECT u FROM UsuarioEntity u WHERE u.dni = :dni")
	UsuarioEntity getUsuarioFromDni(@Param("dni") String dni);
}
