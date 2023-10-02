package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.LibroEntity;
import entity.PrestamoEntity;
import entity.UsuarioEntity;
import provider.PrestamoLibroProvider;
import provider.PrestamoProvider;
import provider.UsuarioProvider;
import repository.LibroRepository;
import repository.PrestamoRepository;

//faltan muchas validaciones
@Service
public class PrestamoProviderImpl implements PrestamoProvider{
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private UsuarioProvider usuarioProvider;
	@Autowired
	private PrestamoLibroProvider plProvider;


	@Override
	public List<PrestamoEntity> listarPrestamos() {
		return prestamoRepository.findAll();
	}

	@Override
	public PrestamoEntity anadirPrestamo(PrestamoEntity prestamo) {
		return this.prestamoRepository.save(prestamo);
	}

	@Override
	public PrestamoEntity buscarPrestamoId(int prestamoId) {
		return this.prestamoRepository.getReferenceById(prestamoId);
	}

	@Override
	public PrestamoEntity editarPrestamo(PrestamoEntity prestamo, int prestamoId) {
		PrestamoEntity prestamoDB = prestamoRepository.getReferenceById(prestamoId);

		prestamoDB.setFechaDevolucion(prestamo.getFechaDevolucion());

		if (Objects.nonNull(prestamo.getFechaPrestamo())) {
			prestamoDB.setFechaPrestamo(prestamo.getFechaPrestamo());
		}

		UsuarioEntity usuAux = usuarioProvider.buscarUsuarioId(prestamo.getId());
		
		if (Objects.nonNull(usuAux)) {
			prestamoDB.setIdUsuario(usuAux.getId());
		}

		// No tengo claro que todas estas comprobaciones sean ncesarias o correctas
		
		List<LibroEntity> libros = plProvider.buscarLibrosPorPrestamoId(prestamoId);
		LibroEntity libroAux;
		boolean librosCorrectos = true;
		for (LibroEntity lib : libros) {
			libroAux = libroRepository.getReferenceById(lib.getId());
			if (libroAux == null) {
				librosCorrectos = false;
			}
		}
		if (librosCorrectos) {
			prestamoDB.setLibros(prestamo.getLibros());
		}
		
		return prestamoRepository.save(prestamoDB);
	}

	@Override
	public void deletePrestamoById(int prestamoId) {
		this.prestamoRepository.deleteById(prestamoId);
		
	}

	@Override
	public List<LibroEntity> listarLibrosPrestamo(int prestamoId) {
        return plProvider.buscarLibrosPorPrestamoId(prestamoId);
	}

	
}
