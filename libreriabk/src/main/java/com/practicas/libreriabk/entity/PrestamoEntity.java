package com.practicas.libreriabk.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRESTAMO")
@Getter
@Setter
public class PrestamoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="fecha_devolucion")
    private String fechaDevolucion;
	
	@Column(nullable = false, name="fecha_prestamo")
    private String fechaPrestamo;
	
	
	@Column(name="id_usuario") 
	private int idUsuario;
	
	@ManyToOne
	@JoinColumn(name="id_usuario", referencedColumnName="id", insertable = false, updatable = false) 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase
	private UsuarioEntity usuario;
    
	@OneToMany(mappedBy = "idPrestamo", cascade = CascadeType.ALL) 
	//mappedBY indica el nombre del atributo al cual referencia en la otra clase java
	private List<PrestamoLibroEntity> libros;
	
	
  
}
