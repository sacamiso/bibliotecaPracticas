package providerImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import entity.LibroEntity;
import entity.PrestamoEntity;
import entity.PrestamoLibroEntity;
import provider.PrestamoLibroProvider;
import repository.LibroRepository;
import repository.PrestamoLibroRepository;
import repository.PrestamoRepository;

//faltan muchas validaciones
@Service
public class PrestamoLibroProviderImpl implements PrestamoLibroProvider{

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
		return plRepository.getReferenceById(libroId,prestamoId);
	}

	@Override
	public PrestamoLibroEntity editarPrestamoLibro(PrestamoLibroEntity prestamoLibro, int libroId, int prestamoId) {
		PrestamoLibroEntity plDB = plRepository.getReferenceById(libroId, prestamoId);


		PrestamoEntity pres = prestamoRepository.getReferenceById(prestamoLibro.getIdPrestamo());
		if (pres != null) {
			plDB.setIdPrestamo(pres.getId());
		}
		
		LibroEntity libro = libroRepository.getReferenceById(prestamoLibro.getIdLibro());
		if (libro != null) {
			plDB.setIdLibro(pres.getId());
		}

		return plRepository.save(plDB);

	}

	@Override
	public void deletePrestamoLibroById(int libroId, int prestamoId) {
		plRepository.deleteById(libroId, prestamoId);
	}

	@Override
	public List<PrestamoEntity> buscarPrestamosPorLibroId(int libroId) {
		List<PrestamoLibroEntity> plAuxList = this.listarPrestamosLibros();
		List<PrestamoEntity> listaPrestamos = new ArrayList<PrestamoEntity>();
		for(PrestamoLibroEntity pl : plAuxList) {
			if(pl.getIdLibro()== libroId) {
				listaPrestamos.add(this.prestamoRepository.getReferenceById(libroId));
			}
		}
		return listaPrestamos;
	}

	@Override
	public List<LibroEntity> buscarLibrosPorPrestamoId(int prestamoId) {
		List<PrestamoLibroEntity> plAuxList = this.listarPrestamosLibros();
		List<LibroEntity> listaLibros = new ArrayList<LibroEntity>();
		for(PrestamoLibroEntity pl : plAuxList) {
			if(pl.getIdPrestamo()== prestamoId) {
				listaLibros.add(this.libroRepository.getReferenceById(prestamoId));
			}
		}
		return listaLibros;
	}

}
