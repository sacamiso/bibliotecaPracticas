package com.practicas.libreriabk.controller;

import java.util.List;

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

import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.provider.UsuarioProvider;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioProvider usuarioProvider;	
	
	@GetMapping("/usuario/all")
	public ResponseEntity<List<UsuarioDto>> listarUsuarios(){
		return new ResponseEntity<List<UsuarioDto>>(this.usuarioProvider.listarUsuarios(),HttpStatus.OK);
	}

	@PostMapping("/usuario/add")
	public ResponseEntity<UsuarioDto> anadirUsuario(@RequestBody @Valid UsuarioDto usuario) {
		UsuarioDto uDto = this.usuarioProvider.anadirUsuario(usuario);
		if(uDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(uDto,HttpStatus.OK);
	}

	@GetMapping("/usuario/getById/{id}")
	public ResponseEntity<UsuarioDto> buscarUsuarioId(@PathVariable("id") int usuarioId) {
		UsuarioDto u = this.usuarioProvider.buscarUsuarioId(usuarioId);
		if(u ==  null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDto>(u, HttpStatus.OK);
	}

	@PutMapping("/usuario/editar/{id}")
	public ResponseEntity<UsuarioDto> editarUsuario(@RequestBody @Valid UsuarioDto usuario,@PathVariable("id") int usuarioId) {
		
		UsuarioDto uDto = this.usuarioProvider.editarUsuario(usuario, usuarioId);
		if(uDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(uDto ,HttpStatus.OK);
	}

	@DeleteMapping("/usuario/delete/{id}")
	public ResponseEntity<String> deleteUsuarioById(@PathVariable("id") int usuarioId) {
		this.usuarioProvider.deleteUsuarioById(usuarioId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}
	
	@DeleteMapping("/usuario/logicDelete/{id}")
	public ResponseEntity<UsuarioDto> logicDeleteUsuarioById(@PathVariable("id") int usuarioId) {
		UsuarioDto uDto = this.usuarioProvider.logicDeleteUsuarioById(usuarioId);
		if(uDto ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UsuarioDto>(uDto,HttpStatus.OK);
	}

	
}
