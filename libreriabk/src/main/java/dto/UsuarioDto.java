package dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDto {
	private int id;
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int telefono;
	private String email;
	private List<PrestamoDto> listaPrestamos;
}
