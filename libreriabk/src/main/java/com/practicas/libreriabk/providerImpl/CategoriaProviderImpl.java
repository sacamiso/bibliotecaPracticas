package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.entity.CategoriaEntity;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.provider.CategoriaProvider;
import com.practicas.libreriabk.repository.CategoriaRepository;

@Service
public class CategoriaProviderImpl implements CategoriaProvider {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public List<CategoriaEntity> listarCategorias() {
		return this.categoriaRepository.findAll();
	}

	@Override
	public CategoriaEntity anadirCategoria(CategoriaEntity categoria) {
		return this.categoriaRepository.save(categoria);
	}

	@Override
	public CategoriaEntity buscarCategoriaId(int categoriaId) {
		if(!categoriaRepository.findById(categoriaId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return this.categoriaRepository.getReferenceById(categoriaId);
	}

	@Override
	public CategoriaEntity editarCategoria(CategoriaEntity categoria, int categoriaId) {
		
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

		return this.categoriaRepository.save(categoriaDB);
	}

	@Override
	public void deleteCategoriaById(int categoriaId) {
		this.categoriaRepository.deleteById(categoriaId);
	}

	@Override
	public List<LibroEntity> listarLibrosCategoria(int categoriaId) {
		if(!categoriaRepository.findById(categoriaId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		CategoriaEntity categoriaBD = this.categoriaRepository.getReferenceById(categoriaId);
        return categoriaBD.getListaLibros();
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
