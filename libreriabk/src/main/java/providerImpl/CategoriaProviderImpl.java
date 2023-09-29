package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.AutorEntity;
import entity.CategoriaEntity;
import entity.LibroEntity;
import provider.CategoriaProvider;
import repository.CategoriaRepository;
import repository.LibroRepository;

//faltan muchas validaciones
@Service
public class CategoriaProviderImpl implements CategoriaProvider {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LibroRepository libroRepository;

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
		return this.categoriaRepository.getReferenceById(categoriaId);
	}

	@Override
	public CategoriaEntity editarCategoria(CategoriaEntity categoria, int categoriaId) {
		CategoriaEntity categoriaDB = this.categoriaRepository.getReferenceById(categoriaId);

		if (Objects.nonNull(categoria.getDescripcion()) && !"".equalsIgnoreCase(categoria.getDescripcion())) {
			categoriaDB.setDescripcion(categoria.getDescripcion());
		}
		if (Objects.nonNull(categoria.getNombre()) && !"".equalsIgnoreCase(categoria.getNombre())) {
			categoriaDB.setNombre(categoria.getNombre());
			// Falta asegurarse que no existe ninguna categoria con ese mismo nombre
		}

		// No tengo claro que todas estas comprobaciones sean ncesarias o correctas
		List<LibroEntity> libros = categoria.getListaLibros();
		LibroEntity libroAux;
		boolean librosCorrectos = true;
		for (LibroEntity libro : libros) {
			libroAux = libroRepository.getReferenceById(libro.getId());
			if (libro == null) {
				librosCorrectos = false;
			}
		}
		if (librosCorrectos) {
			categoriaDB.setListaLibros(libros);
		}
		return this.categoriaRepository.save(categoriaDB);
	}

	@Override
	public void deleteCategoriaById(int categoriaId) {
		this.categoriaRepository.deleteById(categoriaId);
	}

	@Override
	public List<LibroEntity> listarLibrosCategoria(int categoriaId) {
		CategoriaEntity categoriaBD = this.categoriaRepository.getReferenceById(categoriaId);
        return categoriaBD.getListaLibros();
	}

}
