package com.practicas.libreriabk.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UsuarioDto {
	
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
    
    private boolean activo;
}
