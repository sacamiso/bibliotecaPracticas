package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.LibroDto;
import dto.PrestamoDto;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;
import provider.PrestamoProvider;

@RestController
public class LibroController {
	
	@Autowired
	private LibroProvider libroProvider;
	
	@Autowired
	private PrestamoProvider prestamoProvider;
	
	@GetMapping("/libro/all")
	public List<LibroDto> listarLibros(){
		List<LibroEntity> libEnt = this.libroProvider.listarLibros();
		return libEnt.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList());
	}
	
	@PostMapping("/libro/add")
    public LibroDto anadirLibro(@RequestBody @Valid LibroDto libro) {
		LibroEntity libEnt = this.libroProvider.anadirLibro(libroProvider.convertToEntityLibro(libro));
		return libroProvider.convertToDtoLibro(libEnt);
	}
	
	@GetMapping("/libro/getById/{id}")
	public LibroDto buscarLibroId(@PathVariable("id") int libroId) {
		return libroProvider.convertToDtoLibro(this.libroProvider.buscarLibroId(libroId));
	}
	
	@PutMapping("/libro/editar/{id}")
	public LibroDto editarLibro(@RequestBody @Valid LibroDto libro, @PathVariable("id") int libroId) {
		LibroEntity libEnt = this.libroProvider.editarLibro(libroProvider.convertToEntityLibro(libro), libroId);
		return libroProvider.convertToDtoLibro(libEnt);
	}
	
	@DeleteMapping("/libro/delete/{id}")
	public String deleteLibroById(@PathVariable("id") int libroId) {
		this.libroProvider.deleteLibroById(libroId);
		return "Eliminado correctamente";
	}
	
	@GetMapping("/prestamo/libro/{id}")
	public List<PrestamoDto> listarPrestamosLibro(@PathVariable("id") int libroId){
		List<PrestamoEntity> listaAux = this.libroProvider.listarPrestamosLibro(libroId);
		return listaAux.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList());
	}

}
