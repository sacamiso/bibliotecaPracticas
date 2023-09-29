package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.AutorEntity;
import entity.LibroEntity;
import provider.AutorProvider;
import repository.AutorRepository;
import repository.LibroRepository;

//faltan muchas validaciones
@Service
public class AutorProviderImpl implements AutorProvider {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LibroRepository libroRepository;

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
		return autorRepository.getReferenceById(autorId);
	}

	@Override
	public AutorEntity editarAutor(AutorEntity autor, int autorId) {
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
			//Falta asegurarse que no existe ningun autor con ese mismo DNI
		}
		
		//No tengo claro que todas estas comprobaciones sean ncesarias o correctas
		List<LibroEntity> libros = autor.getListaLibros();
		LibroEntity libroAux;
		boolean librosCorrectos = true;
		for(LibroEntity libro : libros){
			libroAux = libroRepository.getReferenceById(libro.getId());
			if(libro==null){
				librosCorrectos = false;
			}
		}
		if(librosCorrectos){
			autorDB.setListaLibros(libros);
		}
        return autorRepository.save(autorDB);
	}

	@Override
	public void deleteAutorById(int autorId) {
		autorRepository.deleteById(autorId);

	}

	@Override
	public List<LibroEntity> listarLibrosAutor(int autorId) {
		AutorEntity autorDB = autorRepository.getReferenceById(autorId);
        return autorDB.getListaLibros();
	}

}
