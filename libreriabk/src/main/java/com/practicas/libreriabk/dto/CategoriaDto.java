package com.practicas.libreriabk.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoriaDto {
	
	@NotNull(message = "El id no puede ser nulo")
	@NotEmpty(message = "El id no puede estar vacío")
	private int id;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@NotEmpty(message = "El nombre no puede estar vacío")
	private String nombre;
	
	@NotNull(message = "La descripción no puede ser nula")
	@NotEmpty(message = "La descripción no puede estar vacía")
	private String descripcion;
	private List<LibroDto> listaLibros;

}
