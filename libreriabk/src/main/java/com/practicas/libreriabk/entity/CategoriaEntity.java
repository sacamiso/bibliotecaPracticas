package com.practicas.libreriabk.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIA")
@Getter 
@Setter
public class CategoriaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(nullable = false, unique = true)
    private String nombre;
	
	@Column(nullable = false)
    private String descripcion;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) 
	//mappedBY indica el nombre del atributo al cual referencia en la otra clase java
	private List<LibroEntity> listaLibros;

}
