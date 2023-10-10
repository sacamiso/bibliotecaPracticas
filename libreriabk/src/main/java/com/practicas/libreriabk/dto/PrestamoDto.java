package com.practicas.libreriabk.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class PrestamoDto {
	
	private int idPrestamo;
	
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de fecha debe ser dd/mm/aaaa")
	private String devolucion;
    
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "El formato de fecha debe ser dd/mm/aaaa")
    @NotNull(message = "La fecha de prestamo no puede ser nula")
	@NotEmpty(message = "La fecha de prestamo no puede estar vacía")
    private String prestamo;
    
    @NotNull(message = "El id del usuario no puede ser nulo")
	@NotEmpty(message = "El id del usuario no puede estar vacío")
	private int idUsuario;
    
	private List<LibroDto> libros;

	@Override
	public String toString() {
		return "PrestamoDto [id=" + idPrestamo + ", fechaDevolucion=" + devolucion + ", fechaPrestamo=" + prestamo
				+ ", idUsuario=" + idUsuario + ", libros=" + libros + "]";
	}
	
	

}
