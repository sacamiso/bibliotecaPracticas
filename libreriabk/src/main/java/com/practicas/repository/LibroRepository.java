package com.practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.entity.LibroEntity;

public interface LibroRepository extends JpaRepository<LibroEntity, Integer>{

	
}
