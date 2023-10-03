package controller;

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
	public ResponseEntity<List<LibroDto>> listarLibros(){
		List<LibroEntity> libEnt = this.libroProvider.listarLibros();
		return new ResponseEntity<List<LibroDto>>(libEnt.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList()),HttpStatus.OK);
	}
	
	@PostMapping("/libro/add")
    public ResponseEntity<LibroDto> anadirLibro(@RequestBody @Valid LibroDto libro) {
		LibroEntity libEnt = this.libroProvider.anadirLibro(libroProvider.convertToEntityLibro(libro));
		if(libEnt ==  null) {
			new ResponseEntity<>(libEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(libroProvider.convertToDtoLibro(libEnt), HttpStatus.OK);
	}
	
	@GetMapping("/libro/getById/{id}")
	public ResponseEntity<LibroDto> buscarLibroId(@PathVariable("id") int libroId) {
		LibroEntity l = this.libroProvider.buscarLibroId(libroId);
		if(l ==  null) {
			new ResponseEntity<>(l,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(libroProvider.convertToDtoLibro(l),HttpStatus.OK);
	}
	
	@PutMapping("/libro/editar/{id}")
	public ResponseEntity<LibroDto> editarLibro(@RequestBody @Valid LibroDto libro, @PathVariable("id") int libroId) {
		LibroEntity libEnt = this.libroProvider.editarLibro(libroProvider.convertToEntityLibro(libro), libroId);
		if(libEnt ==  null) {
			new ResponseEntity<>(libEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LibroDto>(libroProvider.convertToDtoLibro(libEnt),HttpStatus.OK);
	}
	
	@DeleteMapping("/libro/delete/{id}")
	public ResponseEntity<String> deleteLibroById(@PathVariable("id") int libroId) {
		this.libroProvider.deleteLibroById(libroId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}
	
	@GetMapping("/prestamo/libro/{id}")
	public ResponseEntity<List<PrestamoDto>> listarPrestamosLibro(@PathVariable("id") int libroId){
		List<PrestamoEntity> listaAux = this.libroProvider.listarPrestamosLibro(libroId);
		if(listaAux==null) {
			new ResponseEntity<>(listaAux,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PrestamoDto>>(listaAux.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList()),HttpStatus.OK);
	}

}
