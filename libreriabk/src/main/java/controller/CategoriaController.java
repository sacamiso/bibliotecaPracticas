package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CategoriaDto;
import dto.LibroDto;
import entity.CategoriaEntity;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.CategoriaProvider;

@RestController
public class CategoriaController {

	@Autowired
	private CategoriaProvider categoriaProvider;

	@Autowired
	private ModelMapper modelMapper;

	// Dudas
	private CategoriaDto convertToDtoCategoria(CategoriaEntity catE) {
		CategoriaDto catDto = modelMapper.map(catE, CategoriaDto.class);
		return catDto;
	}

	// Dudas
	private CategoriaEntity convertToEntityCategoria(CategoriaDto catDto) {
		CategoriaEntity catEntity = modelMapper.map(catDto, CategoriaEntity.class);
		return catEntity;
	}
	
	private LibroDto convertToDtoLibro(LibroEntity libroE) {
		LibroDto libroDto = modelMapper.map(libroE, LibroDto.class);
	    return libroDto;
	}

	@GetMapping("/categoria/all")
	public List<CategoriaDto> listarCategorias(){
		List<CategoriaEntity> catEnt = this.categoriaProvider.listarCategorias();
		return catEnt.stream().map(this::convertToDtoCategoria).collect(Collectors.toList());
		
	}

	@PostMapping("/categoria/add")
	public CategoriaDto anadirCategoria(CategoriaDto categoria) {
		CategoriaEntity cEnt = this.categoriaProvider.anadirCategoria(this.convertToEntityCategoria(categoria));
		return this.convertToDtoCategoria(cEnt);
	}

	@GetMapping("/categoria/getById/{id}")
	public CategoriaDto buscarCategoriaId(int categoriaId) {
		return this.convertToDtoCategoria(this.categoriaProvider.buscarCategoriaId(categoriaId));
	}

	@PutMapping("/categoria/editar/{id}")
	public CategoriaDto editarCategoria(CategoriaDto categoria, int categoriaId) {
		CategoriaEntity cEnt = this.categoriaProvider.editarCategoria(this.convertToEntityCategoria(categoria), categoriaId);
		return this.convertToDtoCategoria(cEnt);
	}

	@DeleteMapping("/categoria/delete/{id}")
	public String deleteCategoriaById(int categoriaId) {
		this.categoriaProvider.deleteCategoriaById(categoriaId);
		return "Eliminado correctamente";
	}

	@GetMapping("/libro/categoria/{id}")
	List<LibroDto> listarLibrosCategoria(int categoriaId){
		List<LibroEntity> listaAux = this.categoriaProvider.listarLibrosCategoria(categoriaId);
		return listaAux.stream().map(this::convertToDtoLibro).collect(Collectors.toList());
	}
}
