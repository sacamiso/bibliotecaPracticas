package providerImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.PrestamoEntity;
import entity.UsuarioEntity;
import provider.UsuarioProvider;
import repository.PrestamoRepository;
import repository.UsuarioRepository;

//faltan muchas validaciones
@Service
public class UsuarioProviderImpl implements UsuarioProvider {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PrestamoRepository prestamoRepository;

	@Override
	public List<UsuarioEntity> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public UsuarioEntity anadirUsuario(UsuarioEntity usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioEntity buscarUsuarioId(int usuarioId) {
		return usuarioRepository.getReferenceById(usuarioId);
	}

	@Override
	public UsuarioEntity editarUsuario(UsuarioEntity usuario, int usuarioId) {
		UsuarioEntity usuarioDB = usuarioRepository.getReferenceById(usuarioId);

		usuarioDB.setTelefono(usuario.getTelefono());
		usuarioDB.setEmail(usuario.getEmail());
		usuarioDB.setApellido2(usuario.getApellido2());

		if (Objects.nonNull(usuario.getApellido1()) && !"".equalsIgnoreCase(usuario.getApellido1())) {
			usuarioDB.setApellido1(usuario.getApellido1());
		}
		if (Objects.nonNull(usuario.getNombre()) && !"".equalsIgnoreCase(usuario.getNombre())) {
			usuarioDB.setNombre(usuario.getNombre());
		}
		if (Objects.nonNull(usuario.getDni()) && !"".equalsIgnoreCase(usuario.getDni())) {
			usuarioDB.setDni(usuario.getDni());
			// Falta asegurarse que no existe ningun usuario con ese mismo DNI
		}

		// No tengo claro que todas estas comprobaciones sean ncesarias o correctas
		List<PrestamoEntity> prestamos = usuario.getListaPrestamos();
		PrestamoEntity presAux;
		boolean prestamosCorrectos = true;
		for (PrestamoEntity prest : prestamos) {
			presAux = prestamoRepository.getReferenceById(prest.getId());
			if (presAux == null) {
				prestamosCorrectos = false;
			}
		}
		if (prestamosCorrectos) {
			usuarioDB.setListaPrestamos(prestamos);
		}
		return usuarioRepository.save(usuarioDB);
	}

	@Override
	public void deleteUsuarioById(int usuarioId) {
		usuarioRepository.deleteById(usuarioId);

	}

	@Override
	public List<PrestamoEntity> listarPrestamosUsuario(int usuarioId) {
		UsuarioEntity usuarioDB = usuarioRepository.getReferenceById(usuarioId);
        return usuarioDB.getListaPrestamos();
	}

}
