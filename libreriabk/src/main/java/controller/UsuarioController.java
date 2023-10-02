package controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import entity.PrestamoEntity;
import entity.UsuarioEntity;
import provider.UsuarioProvider;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioProvider usuarioProvider;

	@Autowired
	private ModelMapper modelMapper;
	
	
	
	List<UsuarioEntity> listarUsuarios();

	UsuarioEntity anadirUsuario(UsuarioEntity usuario);

	UsuarioEntity buscarUsuarioId(int usuarioId);

	UsuarioEntity editarUsuario(UsuarioEntity usuario, int usuarioId);

	void deleteUsuarioById(int usuarioId);

	List<PrestamoEntity> listarPrestamosUsuario(int usuarioId);
}
