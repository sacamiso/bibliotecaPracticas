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

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.provider.CategoriaProvider;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaProvider categoriaProvider;
	
		
	
	@GetMapping("/categoria/all")
	public ResponseEntity<List<CategoriaDto>> listarCategorias(){
		List<CategoriaDto> catDto = this.categoriaProvider.listarCategorias();
		return new ResponseEntity<List<CategoriaDto>>(catDto, HttpStatus.OK);
	}

	@PostMapping("/categoria/add")
	public ResponseEntity<CategoriaDto> anadirCategoria(@RequestBody @Valid CategoriaDto categoria) {
		CategoriaDto cDto = this.categoriaProvider.anadirCategoria(categoria);
		if(cDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(cDto, HttpStatus.OK);
	}

	@GetMapping("/categoria/getById/{id}")
	public ResponseEntity<CategoriaDto> buscarCategoriaId(@PathVariable("id") int categoriaId) {
		CategoriaDto cDto = this.categoriaProvider.buscarCategoriaId(categoriaId);
		if(cDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(cDto, HttpStatus.OK);
	}

	@PutMapping("/categoria/editar/{id}")
	public ResponseEntity<CategoriaDto> editarCategoria(@RequestBody @Valid CategoriaDto categoria,@PathVariable("id") int categoriaId) {
		CategoriaDto cDto = this.categoriaProvider.editarCategoria(categoria, categoriaId);
		if(cDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(cDto, HttpStatus.OK);
	}

	@DeleteMapping("/categoria/delete/{id}")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable("id") int categoriaId) {
		this.categoriaProvider.deleteCategoriaById(categoriaId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/libro/categoria/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosCategoria(@PathVariable("id") int categoriaId){
		List<LibroDto> listaLibros = this.categoriaProvider.listarLibrosCategoria(categoriaId);
		if(listaLibros==null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaLibros, HttpStatus.OK);
	}
}
