package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;

@RestController
public class LibroController {
	
	@Autowired
	private LibroProvider libroProvider;
	
	@GetMapping("/libro/all")
	public List<LibroEntity> listarLibros(){
		return this.libroProvider.listarLibros();
	}
	
	@PostMapping("/libro/add")
    public LibroEntity anadirLibro(@Valid @RequestBody LibroEntity libro) {
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
