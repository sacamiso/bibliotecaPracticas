package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.LibroEntity;

public interface LibroRepository extends JpaRepository<LibroEntity, Integer>{

	
}
