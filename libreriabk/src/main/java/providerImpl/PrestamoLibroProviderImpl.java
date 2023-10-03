package providerImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PrestamoLibroDto;
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
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PrestamoLibroEntity> listarPrestamosLibros() {
		return plRepository.findAll();
	}

	@Override
	public PrestamoLibroEntity anadirLibro(PrestamoLibroEntity prestamoLibro) {
		
		if(!prestamoRepository.findById(prestamoLibro.getIdPrestamo()).isPresent()) {
			return null;
		}

		if (!libroRepository.findById(prestamoLibro.getIdLibro()).isPresent()) {
			return null;
		}
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
	
	@Override
	public PrestamoLibroDto convertToDtoPrestamoLibro(PrestamoLibroEntity plE) {
		PrestamoLibroDto plDto = modelMapper.map(plE, PrestamoLibroDto.class);
	    return plDto;
	}

	@Override
	public PrestamoLibroEntity convertToEntityPrestamoLibro(PrestamoLibroDto plDto) {
		return modelMapper.map(plDto, PrestamoLibroEntity.class);
	}

}
