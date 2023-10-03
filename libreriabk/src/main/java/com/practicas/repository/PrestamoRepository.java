package com.practicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practicas.entity.PrestamoEntity;

public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Integer>{

}
