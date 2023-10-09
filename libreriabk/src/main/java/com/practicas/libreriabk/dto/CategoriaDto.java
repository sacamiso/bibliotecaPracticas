package com.practicas.libreriabk.dto;

import java.util.List;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CategoriaDto {

	private int id;
	
	@NotNull(message = "El nombre no puede ser nulo")
	@NotEmpty(message = "El nombre no puede estar vacío")
	private String nombre;
	
	@NotNull(message = "La descripción no puede ser nula")
	@NotEmpty(message = "La descripción no puede estar vacía")
	private String descripcion;
	
	@EqualsAndHashCode.Exclude
	private List<LibroDto> listaLibros;

}
