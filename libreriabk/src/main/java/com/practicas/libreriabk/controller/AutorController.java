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

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.jasper.AutorJasper;
import com.practicas.libreriabk.jasper.AutorLibrosJasper;
import com.practicas.libreriabk.provider.AutorProvider;

@RestController
public class AutorController {

	@Autowired
	private AutorProvider autorProvider;
	
	@Autowired
	private AutorJasper autorJasper;
	
	@Autowired
	private AutorLibrosJasper autorLibrosJasper;
	
	@GetMapping("/autor/all")
	public ResponseEntity<List<AutorDto>> listarAutores() {
		List<AutorDto> listaAutorDto = this.autorProvider.listarAutores();
		return new ResponseEntity<List<AutorDto>>(listaAutorDto, HttpStatus.OK);
	}

	@PostMapping("/autor/add")
	public ResponseEntity<AutorDto> anadirAutor(@RequestBody @Valid AutorDto autor) {
		AutorDto aDto = this.autorProvider.anadirAutor(autor);
		if(aDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(aDto,HttpStatus.OK);
	}

	@GetMapping("/autor/getById/{id}")
	public ResponseEntity<AutorDto> buscarAutorId(@PathVariable("id") int autorId) {
		AutorDto a = this.autorProvider.buscarAutorId(autorId);
		if(a ==  null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AutorDto>(a, HttpStatus.OK);
	}

	@PutMapping("/autor/editar/{id}")
	public ResponseEntity<AutorDto> editarAutor(@RequestBody @Valid AutorDto autor,@PathVariable("id") int autorId) {
		AutorDto aDto = this.autorProvider.editarAutor(autor, autorId);
		if(aDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(aDto,HttpStatus.OK);
	}

	@DeleteMapping("/autor/delete/{id}")
	public ResponseEntity<String> deleteAutorById(@PathVariable("id") int autorId) {
		this.autorProvider.deleteAutorById(autorId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping("/autor/logicDelete/{id}")
	public ResponseEntity<AutorDto> logicDeleteAutorById(@PathVariable("id") int autorId) {
		AutorDto aDto = this.autorProvider.logicDeleteAutorById(autorId);
		if(aDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<AutorDto>(aDto,HttpStatus.OK);
	}
	
	@GetMapping("/autor/reporte")
	public void reporteAutor() {
		this.autorJasper.generarPDF();
	}
	
	@GetMapping("/autor/reporteLibros")
	public void reporteAutorLibros() {
		this.autorLibrosJasper.generarPDF();
	}

	
}
