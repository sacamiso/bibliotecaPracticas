package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.CategoriaEntity;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.provider.CategoriaProvider;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.repository.CategoriaRepository;

@Service
public class CategoriaProviderImpl implements CategoriaProvider {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LibroProvider libroProvider;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<CategoriaDto> listarCategorias() {
		List<CategoriaEntity> listaCategoriaEnt = this.categoriaRepository.findAll();
		return listaCategoriaEnt.stream().map(this::convertToDtoCategoria).collect(Collectors.toList());
	}

	@Override
	public CategoriaDto anadirCategoria(CategoriaDto categoria) {
		return this.convertToDtoCategoria(this.categoriaRepository.save(this.convertToEntityCategoria(categoria)));
	}

	@Override
	public CategoriaDto buscarCategoriaId(int categoriaId) {
		if(!categoriaRepository.findById(categoriaId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return this.convertToDtoCategoria(this.categoriaRepository.getReferenceById(categoriaId));
	}

	@Override
	public CategoriaDto editarCategoria(CategoriaDto categoria, int categoriaId) {
		
		if(!categoriaRepository.findById(categoriaId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		CategoriaEntity categoriaDB = this.categoriaRepository.getReferenceById(categoriaId);

		if (Objects.nonNull(categoria.getDescripcion()) && !"".equalsIgnoreCase(categoria.getDescripcion())) {
			categoriaDB.setDescripcion(categoria.getDescripcion());
		}
		if (Objects.nonNull(categoria.getNombre()) && !"".equalsIgnoreCase(categoria.getNombre())) {
			categoriaDB.setNombre(categoria.getNombre());
		}

		return this.convertToDtoCategoria(this.categoriaRepository.save(categoriaDB));
	}

	@Override
	public void deleteCategoriaById(int categoriaId) {
		this.categoriaRepository.deleteById(categoriaId);
	}

	@Override
	public List<LibroDto> listarLibrosCategoria(int categoriaId) {
		if(!categoriaRepository.findById(categoriaId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		CategoriaEntity categoriaBD = this.categoriaRepository.getReferenceById(categoriaId);
        List<LibroEntity> listaLibrosEntity = categoriaBD.getListaLibros();
		return listaLibrosEntity.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList());
	}

	@Override
	public CategoriaDto convertToDtoCategoria(CategoriaEntity catE) {
		CategoriaDto catDto = modelMapper.map(catE, CategoriaDto.class);
		return catDto;
	}

	@Override
	public CategoriaEntity convertToEntityCategoria(CategoriaDto catDto) {
		CategoriaEntity catEntity = modelMapper.map(catDto, CategoriaEntity.class);
		return catEntity;
	}

}
