package com.practicas.libreriabk.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PrestamoDto {
	
	@NotNull(message = "El id no puede ser nulo")
	@NotEmpty(message = "El id no puede estar vacío")
	private int id;
	
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de fecha debe ser dd/mm/aaaa")
	private String fechaDevolucion;
    
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de fecha debe ser dd/mm/aaaa")
    @NotNull(message = "La fecha de prestamo no puede ser nula")
	@NotEmpty(message = "La fecha de prestamo no puede estar vacía")
    private String fechaPrestamo;
    
    @NotNull(message = "El id del usuario no puede ser nulo")
	@NotEmpty(message = "El id del usuario no puede estar vacío")
	private int idUsuario;
    
	private List<LibroDto> libros;

}
