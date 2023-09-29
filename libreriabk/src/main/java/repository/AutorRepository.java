package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.AutorEntity;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Integer>{

}
