package com.practicas.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.dto.PrestamoDto;
import com.practicas.dto.UsuarioDto;
import com.practicas.entity.PrestamoEntity;
import com.practicas.entity.UsuarioEntity;
import com.practicas.provider.PrestamoProvider;
import com.practicas.provider.UsuarioProvider;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioProvider usuarioProvider;

	@Autowired
	private PrestamoProvider prestamoProvider;
	
	
	@GetMapping("/usuario/all")
	public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
		List<UsuarioEntity> uEnt = this.usuarioProvider.listarUsuarios();
		return new ResponseEntity<List<UsuarioDto>>(uEnt.stream().map(usuarioProvider::convertToDtoUsuario).collect(Collectors.toList()),HttpStatus.OK);
	}

	@PostMapping("/usuario/add")
	public ResponseEntity<UsuarioDto> anadirUsuario(@RequestBody @Valid UsuarioDto usuario) {
		UsuarioEntity uEnt = this.usuarioProvider.anadirUsuario(usuarioProvider.convertToEntityUsuario(usuario));
		if(uEnt ==  null) {
			new ResponseEntity<>(uEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(usuarioProvider.convertToDtoUsuario(uEnt),HttpStatus.OK);
	}

	@GetMapping("/usuario/getById/{id}")
	public ResponseEntity<UsuarioDto> buscarUsuarioId(@PathVariable("id") int usuarioId) {
		UsuarioDto u = usuarioProvider.convertToDtoUsuario(this.usuarioProvider.buscarUsuarioId(usuarioId));
		if(u ==  null) {
			new ResponseEntity<>(u,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(u, HttpStatus.OK);
	}

	@PutMapping("/usuario/editar/{id}")
	public ResponseEntity<UsuarioDto> editarUsuario(@RequestBody @Valid UsuarioDto usuario,@PathVariable("id") int usuarioId) {
		UsuarioEntity uEnt = this.usuarioProvider.editarUsuario(usuarioProvider.convertToEntityUsuario(usuario), usuarioId);
		if(uEnt ==  null) {
			new ResponseEntity<>(uEnt,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(usuarioProvider.convertToDtoUsuario(uEnt),HttpStatus.OK);
	}

	@DeleteMapping("/usuario/delete/{id}")
	public ResponseEntity<String> deleteUsuarioById(@PathVariable("id") int usuarioId) {
		this.usuarioProvider.deleteUsuarioById(usuarioId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/prestamo/usuario/{id}")
	public ResponseEntity<List<PrestamoDto>> listarPrestamosUsuario(@PathVariable("id") int usuarioId){
		List<PrestamoEntity> listaAux = this.usuarioProvider.listarPrestamosUsuario(usuarioId);
		if(listaAux==null) {
			new ResponseEntity<>(listaAux,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PrestamoDto>>(listaAux.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList()),HttpStatus.OK);
	
	}
}
