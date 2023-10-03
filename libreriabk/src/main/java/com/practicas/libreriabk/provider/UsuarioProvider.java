package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.UsuarioEntity;

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
