package dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class PrestamoDto {
	
	private int id;
	private Date fechaDevolucion;
	private Date fechaPrestamo;
	private int idUsuario;
	private List<LibroDto> libros;

}
