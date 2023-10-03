package com.practicas.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDto {
	
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
    
	@Digits(integer = 9, fraction = 0, message = "El teléfono debe tener 9 dígitos")
	private int telefono;
	
    @Email(message = "El correo electrónico no es válido")
	private String email;
	private List<PrestamoDto> listaPrestamos;
}