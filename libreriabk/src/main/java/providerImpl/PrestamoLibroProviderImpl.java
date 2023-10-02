package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.LibroEntity;
import entity.PrestamoEntity;
import entity.PrestamoLibroEntity;
import entity.PrestamoLibroEntityID;
import provider.PrestamoLibroProvider;
import repository.LibroRepository;
import repository.PrestamoLibroRepository;
import repository.PrestamoRepository;

@Service
public class PrestamoLibroProviderImpl implements PrestamoLibroProvider {

	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private PrestamoLibroRepository plRepository;

	@Override
	public List<PrestamoLibroEntity> listarPrestamosLibros() {
		return plRepository.findAll();
	}

	@Override
	public PrestamoLibroEntity anadirLibro(PrestamoLibroEntity prestamoLibro) {
		return plRepository.save(prestamoLibro);
	}

	@Override
	public PrestamoLibroEntity buscarPrestamoLibroId(int libroId, int prestamoId) {
		PrestamoLibroEntityID clave = new PrestamoLibroEntityID(prestamoId, libroId);
		if(!plRepository.findById(clave).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return plRepository.getReferenceById(clave);
	}

	@Override
	public PrestamoLibroEntity editarPrestamoLibro(PrestamoLibroEntity prestamoLibro, int libroId, int prestamoId) {
		
		PrestamoLibroEntity plDB = this.buscarPrestamoLibroId(libroId, prestamoId);

		if(Objects.isNull(plDB)) {
			return null;
		}
		
		PrestamoEntity pres = prestamoRepository.getReferenceById(prestamoLibro.getIdPrestamo());
		if(prestamoRepository.findById(prestamoLibro.getIdPrestamo()).isPresent()) {
			plDB.setIdPrestamo(pres.getId());
		}

		LibroEntity libro = libroRepository.getReferenceById(prestamoLibro.getIdLibro());
		if (libroRepository.findById(prestamoLibro.getIdLibro()).isPresent()) {
			plDB.setIdLibro(libro.getId());
		}

		return plRepository.save(plDB);

	}

	@Override
	public void deletePrestamoLibroById(int libroId, int prestamoId) {
		PrestamoLibroEntityID clave = new PrestamoLibroEntityID(prestamoId, libroId);
		plRepository.deleteById(clave);
	}

	@Override
	public List<PrestamoEntity> buscarPrestamosPorLibroId(int libroId) {
		return this.plRepository.getPrestamosFromLibro(libroId);
	}

	@Override
	public List<LibroEntity> buscarLibrosPorPrestamoId(int prestamoId) {
		return this.plRepository.getLibrosFromPrestamo(prestamoId);
	}

}
