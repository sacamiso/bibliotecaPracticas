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
import repository.PrestamoRepository;

//faltan muchas validaciones
@Service
public class PrestamoProviderImpl implements PrestamoProvider{
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
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
		
		if(!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		return this.prestamoRepository.getReferenceById(prestamoId);
	}

	@Override
	public PrestamoEntity editarPrestamo(PrestamoEntity prestamo, int prestamoId) {
		
		if(!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		PrestamoEntity prestamoDB = prestamoRepository.getReferenceById(prestamoId);

		prestamoDB.setFechaDevolucion(prestamo.getFechaDevolucion());

		if (Objects.nonNull(prestamo.getFechaPrestamo())) {
			prestamoDB.setFechaPrestamo(prestamo.getFechaPrestamo());
		}

		//Lo compruebo pero no estoy segura de que sea necesario comprobarlo
		UsuarioEntity usuAux = usuarioProvider.buscarUsuarioId(prestamo.getId());
		
		if (Objects.nonNull(usuAux)) {
			prestamoDB.setIdUsuario(usuAux.getId());
		}
		
		return prestamoRepository.save(prestamoDB);
	}

	@Override
	public void deletePrestamoById(int prestamoId) {
		this.prestamoRepository.deleteById(prestamoId);
		
	}

	@Override
	public List<LibroEntity> listarLibrosPrestamo(int prestamoId) {
		if(!prestamoRepository.findById(prestamoId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
        return plProvider.buscarLibrosPorPrestamoId(prestamoId);
	}

	
}
