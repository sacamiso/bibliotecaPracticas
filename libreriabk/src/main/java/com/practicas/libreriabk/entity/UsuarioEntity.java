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
@Table(name = "USUARIO")
@Getter 
@Setter
public class UsuarioEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@Column(nullable = false)
    private boolean activo;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	//mappedBY indica el nombre del atributo al cual referencia en la otra clase java
	private List<PrestamoEntity> listaPrestamos;

	@Override
	public String toString() {
		return "UsuarioEntity [id=" + id + ", telefono=" + telefono + ", email=" + email + ", apellido2=" + apellido2
				+ ", apellido1=" + apellido1 + ", nombre=" + nombre + ", dni=" + dni + "]";
	}
	
	

}
