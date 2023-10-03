package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.AutorDto;
import dto.LibroDto;
import entity.AutorEntity;
import entity.LibroEntity;
import provider.AutorProvider;

@RestController
public class AutorController {

	@Autowired
	private AutorProvider autorProvider;

	@Autowired
	private ModelMapper modelMapper;

	// Dudas
	private AutorDto convertToDtoAutor(AutorEntity auE) {
		return modelMapper.map(auE, AutorDto.class);
	}

	// Dudas
	private AutorEntity convertToEntityAutor(AutorDto auDto) {
		return modelMapper.map(auDto, AutorEntity.class);
	}

	private LibroDto convertToDtoLibro(LibroEntity liE) {
		return modelMapper.map(liE, LibroDto.class);
	}

	@GetMapping("/autor/all")
	public List<AutorDto> listarAutores() {
		List<AutorEntity> aEnt = this.autorProvider.listarAutores();
		return aEnt.stream().map(this::convertToDtoAutor).collect(Collectors.toList());
	}

	@PostMapping("/autor/add")
	public AutorDto anadirAutor(@RequestBody @Valid AutorDto autor) {
		AutorEntity aEnt = this.autorProvider.anadirAutor(this.convertToEntityAutor(autor));
		return this.convertToDtoAutor(aEnt);
	}

	@GetMapping("/autor/getById/{id}")
	public AutorDto buscarAutorId(@PathVariable("id") int autorId) {
		return this.convertToDtoAutor(this.autorProvider.buscarAutorId(autorId));
	}

	@PutMapping("/autor/editar/{id}")
	AutorDto editarAutor(@RequestBody @Valid AutorDto autor,@PathVariable("id") int autorId) {
		AutorEntity aEnt = this.autorProvider.editarAutor(this.convertToEntityAutor(autor), autorId);
		return this.convertToDtoAutor(aEnt);
	}

	@DeleteMapping("/autor/delete/{id}")
	public String deleteAutorById(@PathVariable("id") int autorId) {
		this.autorProvider.deleteAutorById(autorId);
		return "Eliminado correctamente";
	}

	@GetMapping("/libro/autor/{id}")
	public List<LibroDto> listarLibrosAutor(@PathVariable("id") int autorId) {
		List<LibroEntity> listaAux = this.autorProvider.listarLibrosAutor(autorId);
		return listaAux.stream().map(this::convertToDtoLibro).collect(Collectors.toList());
	}
}
