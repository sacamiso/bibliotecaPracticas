package com.practicas.libreriabk.provider;

import java.util.List;

import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.entity.UsuarioEntity;

public interface UsuarioProvider {
	
	List<UsuarioDto> listarUsuarios();
	UsuarioDto anadirUsuario(UsuarioDto usuario);
	UsuarioDto buscarUsuarioId(int usuarioId);
	UsuarioDto editarUsuario(UsuarioDto usuario, int usuarioId);
	void deleteUsuarioById(int usuarioId);
	List<PrestamoDto> listarPrestamosUsuario(int usuarioId);
	UsuarioDto convertToDtoUsuario(UsuarioEntity uE);
	UsuarioEntity convertToEntityUsuario(UsuarioDto uDto);
}
