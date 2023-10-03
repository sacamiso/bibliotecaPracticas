package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.libreriabk.entity.LibroEntity;

public interface LibroRepository extends JpaRepository<LibroEntity, Integer>{

	
}
