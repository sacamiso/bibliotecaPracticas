package providerImpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.AutorDto;
import entity.AutorEntity;
import entity.LibroEntity;
import provider.AutorProvider;
import repository.AutorRepository;

@Service
public class AutorProviderImpl implements AutorProvider {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AutorRepository autorRepository;

	@Override
	public List<AutorEntity> listarAutores() {
		return autorRepository.findAll();
	}

	@Override
	public AutorEntity anadirAutor(AutorEntity autor) {
		return autorRepository.save(autor);
	}

	@Override
	public AutorEntity buscarAutorId(int autorId) {
		if(!autorRepository.findById(autorId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return autorRepository.getReferenceById(autorId);
	}

	@Override
	public AutorEntity editarAutor(AutorEntity autor, int autorId) {
		
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
		
        return autorRepository.save(autorDB);
	}

	@Override
	public void deleteAutorById(int autorId) {
		autorRepository.deleteById(autorId);

	}

	@Override
	public List<LibroEntity> listarLibrosAutor(int autorId) {
		if(!autorRepository.findById(autorId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		AutorEntity autorDB = autorRepository.getReferenceById(autorId);
        return autorDB.getListaLibros();
	}

	@Override
	public AutorDto convertToDtoAutor(AutorEntity auE) {
		return modelMapper.map(auE, AutorDto.class);
	}

	@Override
	public AutorEntity convertToEntityAutor(AutorDto auDto) {
		return modelMapper.map(auDto, AutorEntity.class);
	}

}
