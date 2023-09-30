package dto;

import java.util.List;

import lombok.Data;

@Data
public class AutorDto {
	private int id;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private List<LibroDto> listaLibros;
}
