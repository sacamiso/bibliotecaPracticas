package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.CategoriaEntity;
import entity.LibroEntity;
import provider.CategoriaProvider;
import repository.CategoriaRepository;

@Service
public class CategoriaProviderImpl implements CategoriaProvider {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
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

}
