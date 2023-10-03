package dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoriaDto {
	private int id;
	private String nombre;
	private String descripcion;
	private List<LibroDto> listaLibros;

}
