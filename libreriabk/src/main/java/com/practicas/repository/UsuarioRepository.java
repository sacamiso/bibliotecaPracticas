package com.practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
