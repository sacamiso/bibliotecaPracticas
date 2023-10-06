package com.practicas.libreriabk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.provider.AutorProvider;
import com.practicas.libreriabk.provider.CategoriaProvider;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.provider.PrestamoProvider;

@RestController
public class LibroController {
	
	@Autowired
	private LibroProvider libroProvider;
	
	@Autowired
	private AutorProvider autorProvider;
	
	@Autowired
	private PrestamoProvider prestamoProvider;
	
	@Autowired
	private CategoriaProvider categoriaProvider;
		
	@GetMapping("/libro/all")
	public ResponseEntity<List<LibroDto>> listarLibros(){
		List<LibroDto> libDto = this.libroProvider.listarLibros();
		return new ResponseEntity<List<LibroDto>>(libDto,HttpStatus.OK);
	}
	
	@PostMapping("/libro/add")
    public ResponseEntity<LibroDto> anadirLibro(@RequestBody @Valid LibroDto libro) {
		LibroDto libDto = this.libroProvider.anadirLibro(libro);
		if(libDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(libDto, HttpStatus.OK);
	}
	
	@GetMapping("/libro/getById/{id}")
	public ResponseEntity<LibroDto> buscarLibroId(@PathVariable("id") int libroId) {
		LibroDto l = this.libroProvider.buscarLibroId(libroId);
		if(l ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(l,HttpStatus.OK);
	}
	
	@PutMapping("/libro/editar/{id}")
	public ResponseEntity<LibroDto> editarLibro(@RequestBody @Valid LibroDto libro, @PathVariable("id") int libroId) {
		LibroDto libDto = this.libroProvider.editarLibro(libro, libroId);
		if(libDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(libDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/libro/delete/{id}")
	public ResponseEntity<String> deleteLibroById(@PathVariable("id") int libroId) {
		this.libroProvider.deleteLibroById(libroId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}
	
	@GetMapping("/libro/autor/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosAutor(@PathVariable("id") int autorId) {
		List<LibroDto> listaLibroDto = this.autorProvider.listarLibrosAutor(autorId);
		if(listaLibroDto==null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaLibroDto,HttpStatus.OK);
	}
	
	@GetMapping("/libro/categoria/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosCategoria(@PathVariable("id") int categoriaId){
		List<LibroDto> listaLibros = this.categoriaProvider.listarLibrosCategoria(categoriaId);
		if(listaLibros==null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaLibros, HttpStatus.OK);
	}
	
	@GetMapping("/libro/prestamo/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosPrestamo(@PathVariable("id") int prestamoId) {
		List<LibroDto> librosEn = this.prestamoProvider.listarLibrosPrestamo(prestamoId);
		if (librosEn == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(librosEn, HttpStatus.OK);
	}

}
