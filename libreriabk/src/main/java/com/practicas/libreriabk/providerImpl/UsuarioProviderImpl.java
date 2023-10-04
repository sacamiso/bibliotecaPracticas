package com.practicas.libreriabk.providerImpl;

import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.entity.UsuarioEntity;
import com.practicas.libreriabk.provider.UsuarioProvider;
import com.practicas.libreriabk.repository.UsuarioRepository;

@Service
public class UsuarioProviderImpl implements UsuarioProvider {

	private ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		
		if(!usuarioRepository.findById(usuarioId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		
		return usuarioRepository.getReferenceById(usuarioId);
	}

	@Override
	public UsuarioEntity editarUsuario(UsuarioEntity usuario, int usuarioId) {
		
		if(!usuarioRepository.findById(usuarioId).isPresent()) {
			System.out.println("Entra en el casode que noencuentra los datos");
			return null; //no me gusta esto hay que aponerlo mejor
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

		return usuarioRepository.save(usuarioDB);
	}

	@Override
	public void deleteUsuarioById(int usuarioId) {
		usuarioRepository.deleteById(usuarioId);
	}

	@Override
	public List<PrestamoEntity> listarPrestamosUsuario(int usuarioId) {
		if(!usuarioRepository.findById(usuarioId).isPresent()) {
			return null; //no me gusta esto hay que aponerlo mejor
		}
		UsuarioEntity usuarioDB = usuarioRepository.getReferenceById(usuarioId);
        return usuarioDB.getListaPrestamos();
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

}
