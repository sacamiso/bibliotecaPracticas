package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.LibroDto;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;

@RestController
public class LibroController {
	
	@Autowired
	private LibroProvider libroProvider;
	
	@Autowired
    private ModelMapper modelMapper;
	
	private LibroDto convertToDto(LibroEntity libroE) {
		LibroDto libroDto = modelMapper.map(libroE, LibroDto.class);
		//Creo que esto no está bien por tema de conversión de listas 
	    return libroDto;
	}
	
	@GetMapping("/libro/all")
	public List<LibroDto> listarLibros(){
		List<LibroEntity> libEnt = this.libroProvider.listarLibros();
		return libEnt.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	@PostMapping("/libro/add")
    public LibroEntity anadirLibro(/*(No entiendo porque no lo reconoce)@Valid*/ @RequestBody LibroEntity libro) {
		return this.libroProvider.anadirLibro(libro);
	}
	
	@GetMapping("/libro/getById/{id}")
	public LibroEntity buscarLibroId(@PathVariable("id") int libroId) {
		return this.libroProvider.buscarLibroId(libroId);
	}
	
	@PutMapping("/libro/editar/{id}")
	public LibroEntity editarLibro(@RequestBody LibroEntity libro, @PathVariable("id") int libroId) {
		return this.libroProvider.editarLibro(libro, libroId);
	}
	
	@DeleteMapping("/libro/delete/{id}")
	public String deleteLibroById(@PathVariable("id") int libroId) {
		this.libroProvider.deleteLibroById(libroId);
		return "Eliminado correctamente";
	}
	
	@GetMapping("/prestamo/libro/{id}")
	public List<PrestamoEntity> listarPrestamosLibro(@PathVariable("id") int libroId){
		return this.libroProvider.listarPrestamosLibro(libroId);
	}

}
