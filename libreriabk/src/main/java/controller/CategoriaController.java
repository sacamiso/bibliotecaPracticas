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
	public List<CategoriaDto> listarCategorias(){
		List<CategoriaEntity> catEnt = this.categoriaProvider.listarCategorias();
		return catEnt.stream().map(categoriaProvider::convertToDtoCategoria).collect(Collectors.toList());
	}

	@PostMapping("/categoria/add")
	public CategoriaDto anadirCategoria(@RequestBody @Valid CategoriaDto categoria) {
		CategoriaEntity cEnt = this.categoriaProvider.anadirCategoria(categoriaProvider.convertToEntityCategoria(categoria));
		return categoriaProvider.convertToDtoCategoria(cEnt);
	}

	@GetMapping("/categoria/getById/{id}")
	public CategoriaDto buscarCategoriaId(@PathVariable("id") int categoriaId) {
		return categoriaProvider.convertToDtoCategoria(this.categoriaProvider.buscarCategoriaId(categoriaId));
	}

	@PutMapping("/categoria/editar/{id}")
	public CategoriaDto editarCategoria(@RequestBody @Valid CategoriaDto categoria,@PathVariable("id") int categoriaId) {
		CategoriaEntity cEnt = this.categoriaProvider.editarCategoria(categoriaProvider.convertToEntityCategoria(categoria), categoriaId);
		return categoriaProvider.convertToDtoCategoria(cEnt);
	}

	@DeleteMapping("/categoria/delete/{id}")
	public String deleteCategoriaById(@PathVariable("id") int categoriaId) {
		this.categoriaProvider.deleteCategoriaById(categoriaId);
		return "Eliminado correctamente";
	}

	@GetMapping("/libro/categoria/{id}")
	List<LibroDto> listarLibrosCategoria(@PathVariable("id") int categoriaId){
		List<LibroEntity> listaAux = this.categoriaProvider.listarLibrosCategoria(categoriaId);
		return listaAux.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList());
	}
}
