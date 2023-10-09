package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.libreriabk.entity.LibroEntity;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Integer>{

	
}
