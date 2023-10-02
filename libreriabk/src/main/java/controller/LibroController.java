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
import dto.PrestamoDto;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;

@RestController
public class LibroController {
	
	@Autowired
	private LibroProvider libroProvider;
	
	@Autowired
    private ModelMapper modelMapper;
	
	//Dudas
	private LibroDto convertToDtoLibro(LibroEntity libroE) {
		LibroDto libroDto = modelMapper.map(libroE, LibroDto.class);
	    return libroDto;
	}
	
	//Dudas
	private LibroEntity convertToEntityLibro(LibroDto libroDto) {
		LibroEntity libroEntity = modelMapper.map(libroDto, LibroEntity.class);
	    return libroEntity;
	}
	
	private PrestamoDto convertToDtoPrestamo(PrestamoEntity prestamoE) {
		PrestamoDto presDto = modelMapper.map(prestamoE, PrestamoDto.class);
	    return presDto;
	}
	
	@GetMapping("/libro/all")
	public List<LibroDto> listarLibros(){
		List<LibroEntity> libEnt = this.libroProvider.listarLibros();
		return libEnt.stream().map(this::convertToDtoLibro).collect(Collectors.toList());
	}
	
	@PostMapping("/libro/add")
    public LibroDto anadirLibro(/*(No entiendo porque no lo reconoce)@Valid*/ @RequestBody LibroDto libro) {
		LibroEntity libEnt = this.libroProvider.anadirLibro(this.convertToEntityLibro(libro));
		return this.convertToDtoLibro(libEnt);
	}
	
	@GetMapping("/libro/getById/{id}")
	public LibroDto buscarLibroId(@PathVariable("id") int libroId) {
		return this.convertToDtoLibro(this.libroProvider.buscarLibroId(libroId));
	}
	
	@PutMapping("/libro/editar/{id}")
	public LibroDto editarLibro(@RequestBody LibroDto libro, @PathVariable("id") int libroId) {
		LibroEntity libEnt = this.libroProvider.editarLibro(this.convertToEntityLibro(libro), libroId);
		return this.convertToDtoLibro(libEnt);
	}
	
	@DeleteMapping("/libro/delete/{id}")
	public String deleteLibroById(@PathVariable("id") int libroId) {
		this.libroProvider.deleteLibroById(libroId);
		return "Eliminado correctamente";
	}
	
	@GetMapping("/prestamo/libro/{id}")
	public List<PrestamoDto> listarPrestamosLibro(@PathVariable("id") int libroId){
		List<PrestamoEntity> listaAux = this.libroProvider.listarPrestamosLibro(libroId);
		return listaAux.stream().map(this::convertToDtoPrestamo).collect(Collectors.toList());
	}

}
