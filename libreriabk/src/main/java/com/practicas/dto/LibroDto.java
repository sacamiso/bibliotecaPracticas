package com.practicas.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LibroDto {
	private int id;
	private String titulo;
	private int edicion;
	private int idAutor;
	private int idCategoria;
	private List<PrestamoDto> prestamos;
}
