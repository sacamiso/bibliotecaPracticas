package com.practicas.libreriabk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practicas.libreriabk.entity.PrestamoEntity;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Integer>{

}
