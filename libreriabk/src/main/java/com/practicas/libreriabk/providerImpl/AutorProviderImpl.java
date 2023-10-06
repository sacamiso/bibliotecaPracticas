package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.entity.AutorEntity;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.provider.AutorProvider;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.repository.AutorRepository;

@Service
public class AutorProviderImpl implements AutorProvider {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LibroProvider libroProvider;

	@Override
	public List<AutorDto> listarAutores() {
		List<AutorEntity> listaAutorEntity = autorRepository.findAll();
		return listaAutorEntity.stream().map(this::convertToDtoAutor).collect(Collectors.toList());
	}

	@Override
	public AutorDto anadirAutor(AutorDto autor) {
		AutorEntity aEnt = autorRepository.save(this.convertToEntityAutor(autor));
		return this.convertToDtoAutor(aEnt);
	}

	@Override
	public AutorDto buscarAutorId(int autorId) {
		
		if(!autorRepository.findById(autorId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return this.convertToDtoAutor(autorRepository.getReferenceById(autorId));
	}

	@Override
	public AutorDto editarAutor(AutorDto autor, int autorId) {
		
		if(!autorRepository.findById(autorId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		AutorEntity autorDB = autorRepository.getReferenceById(autorId);
		
		autorDB.setTelefono(autor.getTelefono());
		autorDB.setEmail(autor.getEmail());
		autorDB.setApellido2(autor.getApellido2());
  
        if (Objects.nonNull(autor.getApellido1()) && !"".equalsIgnoreCase(autor.getApellido1()))
		{
            autorDB.setApellido1(autor.getApellido1());
		}
		if (Objects.nonNull(autor.getNombre()) && !"".equalsIgnoreCase(autor.getNombre()))
		{
            autorDB.setNombre(autor.getNombre());
		}
		if (Objects.nonNull(autor.getDni()) && !"".equalsIgnoreCase(autor.getDni()))
		{
            autorDB.setDni(autor.getDni());
		}
		
        return this.convertToDtoAutor(autorRepository.save(autorDB));
	}

	@Override
	public void deleteAutorById(int autorId) {
		autorRepository.deleteById(autorId);

	}

	@Override
	public List<LibroDto> listarLibrosAutor(int autorId) {
		if(!autorRepository.findById(autorId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		AutorEntity autorDB = autorRepository.getReferenceById(autorId);
		List<LibroEntity> librosEntity = autorDB.getListaLibros();
        return librosEntity.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList());
	}

	@Override
	public AutorDto convertToDtoAutor(AutorEntity auE) {
		return modelMapper.map(auE, AutorDto.class);
	}

	@Override
	public AutorEntity convertToEntityAutor(AutorDto auDto) {
		return modelMapper.map(auDto, AutorEntity.class);
	}

	@Override
	public AutorDto logicDeleteAutorById(int autorId) {
		
		Optional<AutorEntity> autorOpt = autorRepository.findById(autorId);
		
		if(!autorOpt.isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		AutorEntity autorDB = autorOpt.get();
		
		if(autorDB.isActivo()) {
			autorDB.setActivo(false);
		}
		
        return this.convertToDtoAutor(autorRepository.save(autorDB));
	}

}
