package com.practicas.libreriabk.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PrestamoDto {
	
	private int id;
	private String fechaDevolucion;
	private String fechaPrestamo;
	private int idUsuario;
	private List<LibroDto> libros;

}
