package dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PrestamoLibroDto {
	private int idPrestamo;
	private int idLibro;
}
