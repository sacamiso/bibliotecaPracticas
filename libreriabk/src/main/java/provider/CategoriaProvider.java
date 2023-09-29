package provider;

import java.util.List;

import entity.CategoriaEntity;
import entity.LibroEntity;

public interface CategoriaProvider {
	
	List<CategoriaEntity> listarCategorias();
    CategoriaEntity anadirCategoria(CategoriaEntity categoria);
	CategoriaEntity buscarCategoriaId(int categoriaId);
	CategoriaEntity editarCategoria(CategoriaEntity categoria, int categoriaId);
	void deleteCategoriaById(int categoriaId);
	List<LibroEntity> listarLibrosCategoria(int categoriaId);

}
