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

import dto.CategoriaDto;
import dto.LibroDto;
import entity.CategoriaEntity;
import entity.LibroEntity;
import provider.CategoriaProvider;
import provider.LibroProvider;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaProvider categoriaProvider;
	
	@Autowired
	private LibroProvider libroProvider;	
	
	@GetMapping("/categoria/all")
	public ResponseEntity<List<CategoriaDto>> listarCategorias(){
		List<CategoriaEntity> catEnt = this.categoriaProvider.listarCategorias();
		return new ResponseEntity<List<CategoriaDto>>(catEnt.stream().map(categoriaProvider::convertToDtoCategoria).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping("/categoria/add")
	public ResponseEntity<CategoriaDto> anadirCategoria(@RequestBody @Valid CategoriaDto categoria) {
		CategoriaEntity cEnt = this.categoriaProvider.anadirCategoria(categoriaProvider.convertToEntityCategoria(categoria));
		if(cEnt ==  null) {
			new ResponseEntity<>(cEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(categoriaProvider.convertToDtoCategoria(cEnt), HttpStatus.OK);
	}

	@GetMapping("/categoria/getById/{id}")
	public ResponseEntity<CategoriaDto> buscarCategoriaId(@PathVariable("id") int categoriaId) {
		CategoriaEntity cE = this.categoriaProvider.buscarCategoriaId(categoriaId);
		if(cE ==  null) {
			new ResponseEntity<>(cE,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(categoriaProvider.convertToDtoCategoria(cE), HttpStatus.OK);
	}

	@PutMapping("/categoria/editar/{id}")
	public ResponseEntity<CategoriaDto> editarCategoria(@RequestBody @Valid CategoriaDto categoria,@PathVariable("id") int categoriaId) {
		CategoriaEntity cEnt = this.categoriaProvider.editarCategoria(categoriaProvider.convertToEntityCategoria(categoria), categoriaId);
		if(cEnt ==  null) {
			new ResponseEntity<>(cEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CategoriaDto>(categoriaProvider.convertToDtoCategoria(cEnt), HttpStatus.OK);
	}

	@DeleteMapping("/categoria/delete/{id}")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable("id") int categoriaId) {
		this.categoriaProvider.deleteCategoriaById(categoriaId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/libro/categoria/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosCategoria(@PathVariable("id") int categoriaId){
		List<LibroEntity> listaAux = this.categoriaProvider.listarLibrosCategoria(categoriaId);
		if(listaAux==null) {
			new ResponseEntity<>(listaAux,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaAux.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList()), HttpStatus.OK);
	}
}
