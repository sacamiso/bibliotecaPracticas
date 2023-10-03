package com.practicas.libreriabk.providerImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.PrestamoLibroDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntity;
import com.practicas.libreriabk.entity.PrestamoLibroEntityID;
import com.practicas.libreriabk.provider.PrestamoLibroProvider;
import com.practicas.libreriabk.repository.LibroRepository;
import com.practicas.libreriabk.repository.PrestamoLibroRepository;
import com.practicas.libreriabk.repository.PrestamoRepository;

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
