package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REL_PRESTAMO_LIBRO")
@Getter
@Setter
public class PrestamoLibroEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name="idPrestamo", referencedColumnName="id") 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase
    private int idPrestamo;
	
	@Id
	@ManyToOne
	@JoinColumn(name="idLibro", referencedColumnName="id")
    private int idLibro;
}
