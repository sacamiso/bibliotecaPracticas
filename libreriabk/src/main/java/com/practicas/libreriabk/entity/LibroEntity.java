package com.practicas.libreriabk.entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "LIBRO")
@Getter
@Setter
public class LibroEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int idLibro;
    
    @Column(nullable = false)
    private String titulo;
	
	@Column(nullable = false)
    private int edicion;
	
	@Column(name="id_autor") 
	private int idAutor;
	
	@ManyToOne
	@JoinColumn(name="id_autor", referencedColumnName="id", insertable = false, updatable = false) 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase
	private AutorEntity autor;
	
	@Column(name="id_categoria")
	private int idCategoria;
	
	@ManyToOne
	@JoinColumn(name="id_categoria", referencedColumnName="id", insertable = false, updatable = false)
	private CategoriaEntity categoria;
	
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	private List<PrestamoLibroEntity> prestamos;
	

    
}
