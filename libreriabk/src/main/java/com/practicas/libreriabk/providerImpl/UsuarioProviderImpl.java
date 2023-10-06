package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.UsuarioEntity;
import com.practicas.libreriabk.provider.PrestamoProvider;
import com.practicas.libreriabk.provider.UsuarioProvider;
import com.practicas.libreriabk.repository.UsuarioRepository;

@Service
public class UsuarioProviderImpl implements UsuarioProvider {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PrestamoProvider prestamoProvider;

	@Override
	public List<UsuarioDto> listarUsuarios() {
		List<UsuarioEntity> listaUsuarioEntity = usuarioRepository.findAll();
		return listaUsuarioEntity.stream().map(this::convertToDtoUsuario).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto anadirUsuario(UsuarioDto usuario) {
		usuario.setActivo(true);
		UsuarioEntity uEnt = usuarioRepository.save(this.convertToEntityUsuario(usuario));
		return this.convertToDtoUsuario(uEnt);
		
	}

	@Override
	public UsuarioDto buscarUsuarioId(int usuarioId) {
		
		if(!usuarioRepository.findById(usuarioId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		return this.convertToDtoUsuario(usuarioRepository.getReferenceById(usuarioId));
	}

	@Override
	public UsuarioDto editarUsuario(UsuarioDto usuario, int usuarioId) {
		
		if(!usuarioRepository.findById(usuarioId).isPresent()) {
			return null; 
		}
		
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
		}

		UsuarioEntity usuarioDBguardado = usuarioRepository.save(usuarioDB);
		return this.convertToDtoUsuario(usuarioDBguardado);
	}

	@Override
	public void deleteUsuarioById(int usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}

	@Override
	public List<PrestamoDto> listarPrestamosUsuario(int usuarioId) {
		Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuarioId);
		if(!usuarioOpt.isPresent()) {
			return null;
		}
		UsuarioEntity usuarioDB = usuarioOpt.get();
		List<PrestamoEntity> prestamosEntity = usuarioDB.getListaPrestamos();
        return prestamosEntity.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList());
	}

	@Override
	public UsuarioDto convertToDtoUsuario(UsuarioEntity uE) {
		UsuarioDto usuarioDto = modelMapper.map(uE, UsuarioDto.class);
	    return usuarioDto;
	}

	@Override
	public UsuarioEntity convertToEntityUsuario(UsuarioDto uDto) {
		UsuarioEntity uEntity = modelMapper.map(uDto, UsuarioEntity.class);
	    return uEntity;
	}

	@Override
	public UsuarioDto logicDeleteUsuarioById(int usuarioId) {
		Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuarioId);
		if(!usuarioOpt.isPresent()) {
			return null; 
		}
		UsuarioEntity usuarioBD = usuarioOpt.get();
		if(usuarioBD.isActivo()) {
			usuarioBD.setActivo(false);
		}
		return this.convertToDtoUsuario(usuarioRepository.save(usuarioBD));
	}

}
