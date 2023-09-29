package entity;

import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "LIBRO")
@Getter
@Setter
public class LibroEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false)
    private String titulo;
	
	@Column(nullable = false)
    private int edicion;
	
	@ManyToOne
	@JoinColumn(name="idAutor", referencedColumnName="id") 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase
	private int idAutor;
	
	@ManyToOne
	@JoinColumn(name="idCategoria", referencedColumnName="id")
	private int idCategoria;
	
	@OneToMany(mappedBy = "idLibro", cascade = CascadeType.ALL)
	private List<PrestamoLibroEntity> prestamos;
    
}
