package com.practicas.libreriabk.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AutorDto {
	
	@NotNull(message = "El id no puede ser nulo")
	@NotEmpty(message = "El id no puede estar vacío")
	private int id;
	
	@NotNull(message = "El DNI no puede ser nulo")
	@NotEmpty(message = "El DNI no puede estar vacío")
	private String dni;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@NotEmpty(message = "El nombre no puede estar vacío")
	private String nombre;
	
	@NotNull(message = "El primer apellido no puede ser nulo")
	@NotEmpty(message = "El primer apellido no puede estar vacío")
	private String apellido1;
	
	private String apellido2;
	
	private int telefono;
	
    @Email(message = "El correo electrónico no es válido")
	private String email;
    
	private List<LibroDto> listaLibros;
}
