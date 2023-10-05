package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.CategoriaEntity;

public interface CategoriaProvider {
	
	List<CategoriaDto> listarCategorias();
	CategoriaDto anadirCategoria(CategoriaDto categoria);
	CategoriaDto buscarCategoriaId(int categoriaId);
	CategoriaDto editarCategoria(CategoriaDto categoria, int categoriaId);
	void deleteCategoriaById(int categoriaId);
	List<LibroDto> listarLibrosCategoria(int categoriaId);
	CategoriaDto convertToDtoCategoria(CategoriaEntity catE);
	CategoriaEntity convertToEntityCategoria(CategoriaDto catDto);
	
}
