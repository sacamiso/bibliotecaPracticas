package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.AutorEntity;
import entity.CategoriaEntity;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;
import repository.AutorRepository;
import repository.CategoriaRepository;
import repository.LibroRepository;
import repository.PrestamoRepository;

//faltan muchas validaciones
@Service
public class LibroProviderImpl implements LibroProvider {

	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private AutorRepository autorRepository;
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	private PrestamoLibroProviderImpl plProviderimpl;

	@Override
	public List<LibroEntity> listarLibros() {
		return libroRepository.findAll();
	}

	@Override
	public LibroEntity anadirLibro(LibroEntity libro) {
		return libroRepository.save(libro);
	}

	@Override
	public LibroEntity buscarLibroId(int libroId) {
		return libroRepository.getReferenceById(libroId);
	}

	@Override
	public LibroEntity editarLibro(LibroEntity libro, int libroId) {
		LibroEntity libroDB = libroRepository.getReferenceById(libroId);

		if (Objects.nonNull(libro.getTitulo()) && !"".equalsIgnoreCase(libro.getTitulo())) {
			libroDB.setTitulo(libro.getTitulo());
		}
		if (Objects.nonNull(libro.getEdicion())) {
			libroDB.setEdicion(libro.getEdicion());
		}

		AutorEntity autor = autorRepository.getReferenceById(libro.getIdAutor());
		if (autor != null) {
			libroDB.setIdAutor(autor.getId());
		}

		CategoriaEntity categoria = categoriaRepository.getReferenceById(libro.getIdCategoria());
		if (categoria != null) {
			libroDB.setIdCategoria(categoria.getId());
		}
		
		List<PrestamoEntity> prestamos = plProviderimpl.buscarPrestamosPorLibroId(libroId);
		PrestamoEntity presAux;
		boolean prestamosCorrectos = true;
		for (PrestamoEntity prest : prestamos) {
			presAux = prestamoRepository.getReferenceById(prest.getId());
			if (presAux == null) {
				prestamosCorrectos = false;
			}
		}
		if (prestamosCorrectos) {
			libroDB.setPrestamos(libro.getPrestamos());
		}

		return libroRepository.save(libroDB);
	}

	@Override
	public void deleteLibroById(int libroId) {
		libroRepository.deleteById(libroId);
	}

	@Override
	public List<PrestamoEntity> listarPrestamosLibro(int libroId) {
		//Tengo dudas sobre esto
        return plProviderimpl.buscarPrestamosPorLibroId(libroId);
	}

}
