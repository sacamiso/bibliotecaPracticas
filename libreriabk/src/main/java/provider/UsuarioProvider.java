package provider;

import java.util.List;

import dto.UsuarioDto;
import entity.PrestamoEntity;
import entity.UsuarioEntity;

public interface UsuarioProvider {
	
	List<UsuarioEntity> listarUsuarios();
    UsuarioEntity anadirUsuario(UsuarioEntity usuario);
	UsuarioEntity buscarUsuarioId(int usuarioId);
	UsuarioEntity editarUsuario(UsuarioEntity usuario, int usuarioId);
	void deleteUsuarioById(int usuarioId);
	List<PrestamoEntity> listarPrestamosUsuario(int usuarioId);
	UsuarioDto convertToDtoUsuario(UsuarioEntity uE);
	UsuarioEntity convertToEntityUsuario(UsuarioDto uDto);
}
