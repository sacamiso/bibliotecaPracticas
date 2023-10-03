package com.practicas.provider;

import java.util.List;

import com.practicas.dto.CategoriaDto;
import com.practicas.entity.CategoriaEntity;
import com.practicas.entity.LibroEntity;

public interface CategoriaProvider {
	
	List<CategoriaEntity> listarCategorias();
    CategoriaEntity anadirCategoria(CategoriaEntity categoria);
	CategoriaEntity buscarCategoriaId(int categoriaId);
	CategoriaEntity editarCategoria(CategoriaEntity categoria, int categoriaId);
	void deleteCategoriaById(int categoriaId);
	List<LibroEntity> listarLibrosCategoria(int categoriaId);
	CategoriaDto convertToDtoCategoria(CategoriaEntity catE);
	CategoriaEntity convertToEntityCategoria(CategoriaDto catDto);
	
}
