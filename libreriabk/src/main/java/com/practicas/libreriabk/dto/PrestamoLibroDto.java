package com.practicas.libreriabk.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PrestamoLibroDto {
	
	@NotNull(message = "El id del prestamo no puede ser nulo")
	@NotEmpty(message = "El id del prestamo no puede estar vacío")
	private int idPrestamo;
	
	@NotNull(message = "El id del libro no puede ser nulo")
	@NotEmpty(message = "El id del libro no puede estar vacío")
	private int idLibro;
}
