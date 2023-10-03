package com.practicas.libreriabk.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.AutorEntity;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.provider.AutorProvider;
import com.practicas.libreriabk.provider.LibroProvider;

@RestController
public class AutorController {

	@Autowired
	private AutorProvider autorProvider;
	
	@Autowired
	private LibroProvider libroProvider;

	@GetMapping("/autor/all")
	public ResponseEntity<List<AutorDto>> listarAutores() {
		List<AutorEntity> aEnt = this.autorProvider.listarAutores();
		return new ResponseEntity<List<AutorDto>>(aEnt.stream().map(autorProvider::convertToDtoAutor).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/autor/add")
	public ResponseEntity<AutorDto> anadirAutor(@RequestBody @Valid AutorDto autor) {
		AutorEntity aEnt = this.autorProvider.anadirAutor(autorProvider.convertToEntityAutor(autor));
		if(aEnt ==  null) {
			new ResponseEntity<>(aEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(autorProvider.convertToDtoAutor(aEnt),HttpStatus.OK);
	}

	@GetMapping("/autor/getById/{id}")
	public ResponseEntity<AutorDto> buscarAutorId(@PathVariable("id") int autorId) {
		AutorDto a = autorProvider.convertToDtoAutor(this.autorProvider.buscarAutorId(autorId));
		if(a ==  null) {
			new ResponseEntity<>(a,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(a, HttpStatus.OK);
	}

	@PutMapping("/autor/editar/{id}")
	public ResponseEntity<AutorDto> editarAutor(@RequestBody @Valid AutorDto autor,@PathVariable("id") int autorId) {
		AutorEntity aEnt = this.autorProvider.editarAutor(autorProvider.convertToEntityAutor(autor), autorId);
		if(aEnt ==  null) {
			new ResponseEntity<>(aEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(autorProvider.convertToDtoAutor(aEnt),HttpStatus.OK);
	}

	@DeleteMapping("/autor/delete/{id}")
	public ResponseEntity<String> deleteAutorById(@PathVariable("id") int autorId) {
		this.autorProvider.deleteAutorById(autorId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/libro/autor/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosAutor(@PathVariable("id") int autorId) {
		List<LibroEntity> listaAux = this.autorProvider.listarLibrosAutor(autorId);
		if(listaAux==null) {
			new ResponseEntity<>(listaAux,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaAux.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList()),HttpStatus.OK);
	}
}
