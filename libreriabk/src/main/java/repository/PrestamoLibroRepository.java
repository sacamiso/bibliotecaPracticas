package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.PrestamoLibroEntity;

//Hay que modificar algo por la clave compuesta creo que igual hace falt una clase auxiliar
public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibroEntity, Integer>{

}
