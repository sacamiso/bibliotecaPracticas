package com.practicas.libreriabk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "REL_PRESTAMO_LIBRO")
@Getter
@Setter
@IdClass(PrestamoLibroEntityID.class) // Especifica la clase de clave primaria compuesta
public class PrestamoLibroEntity {
	
	@Id
	@Column(name="id_prestamo") 
    private int idPrestamo;
	
	@Id
	@Column(name="id_libro")
    private int idLibro;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_prestamo", referencedColumnName="id", insertable = false, updatable = false) 
	//name es el nombre del atributo en la BD y referencedColumnName es el nombre del atributo al cual referencia en la otra clase Pero es el nombre de la tabla
    private PrestamoEntity prestamo;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_libro", referencedColumnName="id", insertable = false, updatable = false)
    private LibroEntity libro;

	@Override
	public String toString() {
		return "PrestamoLibroEntity [idPrestamo=" + idPrestamo + ", idLibro=" + idLibro + ", prestamo=" + prestamo
				+ ", libro=" + libro + "]";
	}
	
	
}
