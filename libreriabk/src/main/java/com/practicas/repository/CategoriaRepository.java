package com.practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer>{

}
