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
public class LibroDto {
	
	@NotNull(message = "El id no puede ser nulo")
	@NotEmpty(message = "El id no puede estar vacío")
	private int idLibro;
	
	@NotNull(message = "El título no puede ser nulo")
	@NotEmpty(message = "El título no puede estar vacío")
	private String titulo;

	@NotNull(message = "La edición no puede ser nula")
	@NotEmpty(message = "La edición no puede estar vacía")
	private int edicion;
	
	@NotNull(message = "El id del autor no puede ser nulo")
	@NotEmpty(message = "El id del autor no puede estar vacío")
	private int idAutor;
	
	@NotNull(message = "El id de la categoría no puede ser nula")
	@NotEmpty(message = "El id de la categoría no puede estar vacía")
	private int idCategoria;
	
	private List<PrestamoDto> prestamos;

	@Override
	public String toString() {
		return "LibroDto [id=" + idLibro + ", titulo=" + titulo + ", edicion=" + edicion + ", idAutor=" + idAutor
				+ ", idCategoria=" + idCategoria + "]";
	}
	
	
}
