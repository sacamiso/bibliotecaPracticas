package entity;

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
	//mappedBY indica el nombre del atributo al cual referencia en la otra clase java
	private List<PrestamoEntity> listaPrestamos;

}
