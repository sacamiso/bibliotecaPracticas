package dto;

import java.util.List;

import lombok.Data;

@Data
public class LibroDto {
	private int id;
	private String titulo;
	private int edicion;
	private int idAutor;
	private int idCategoria;
	private List<PrestamoDto> prestamos;
}
