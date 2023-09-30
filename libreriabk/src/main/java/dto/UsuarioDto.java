package dto;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioDto {
	//Hasta que punto de conocimiento tiene que haber en un dto
	private int id;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private List<PrestamoDto> listaPrestamos;
}
