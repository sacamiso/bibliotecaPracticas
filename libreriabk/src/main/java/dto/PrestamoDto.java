package dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PrestamoDto {
	
	private int id;
	private Date fechaDevolucion;
	private Date fechaPrestamo;
	private int idUsuario;
	private List<LibroDto> libros;

}
