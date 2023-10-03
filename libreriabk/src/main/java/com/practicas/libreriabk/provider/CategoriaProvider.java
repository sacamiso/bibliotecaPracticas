package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.entity.CategoriaEntity;
import com.practicas.libreriabk.entity.LibroEntity;

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
