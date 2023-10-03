package com.practicas.entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

//Si pong la anotacion de getter y setter a la clase genera a todos los atributos los métodos correspondientes

@Entity
@Table(name = "AUTOR")
@Getter 
@Setter
public class AutorEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column
    private int telefono;
	
	@Column
    private String email;
	
	@Column
    private String apellido2;
	
	@Column(nullable = false)
    private String apellido1;
    
	@Column(nullable = false)
    private String nombre;
    
	@Column(nullable = false, unique = true)
    private String dni;
	
	@OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
	private List<LibroEntity> listaLibros;
	
}
