package dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaDto {
	//Hasta que punto de conocimiento tiene que haber en un dto
	private int id;
	private String nombre;
	private String descripcion;
	private List<LibroDto> listaLibros;

}
