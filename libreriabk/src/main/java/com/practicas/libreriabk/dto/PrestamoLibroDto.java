package com.practicas.libreriabk.dto;

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
public class PrestamoLibroDto {
	
	@NotNull(message = "El id del prestamo no puede ser nulo")
	@NotEmpty(message = "El id del prestamo no puede estar vacío")
	private int idPrestamo;
	
	@NotNull(message = "El id del libro no puede ser nulo")
	@NotEmpty(message = "El id del libro no puede estar vacío")
	private int idLibro;
}
