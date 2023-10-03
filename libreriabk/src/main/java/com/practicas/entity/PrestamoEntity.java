package com.practicas.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRESTAMO")
@Getter
@Setter
public class PrestamoEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
	@Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
    private Date fechaPrestamo;
	
	@ManyToOne
	@Column(name="idUsuario") 
	private int idUsuario;
	
	@ManyToOne
	@JoinColumn(name="idUsuario", referencedColumnName="id") 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase
	private UsuarioEntity usuario;
    
	@OneToMany(mappedBy = "idPrestamo", cascade = CascadeType.ALL) 
	//mappedBY indica el nombre del atributo al cual referencia en la otra clase java
	private List<PrestamoLibroEntity> libros;
	
	
  
}
