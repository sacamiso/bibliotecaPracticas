package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
