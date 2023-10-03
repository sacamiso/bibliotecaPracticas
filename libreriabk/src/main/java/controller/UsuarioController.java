package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.PrestamoDto;
import dto.UsuarioDto;
import entity.PrestamoEntity;
import entity.UsuarioEntity;
import provider.PrestamoProvider;
import provider.UsuarioProvider;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioProvider usuarioProvider;

	@Autowired
	private PrestamoProvider prestamoProvider;
	
	
	@GetMapping("/usuario/all")
	public List<UsuarioDto> listarUsuarios(){
		List<UsuarioEntity> uEnt = this.usuarioProvider.listarUsuarios();
		return uEnt.stream().map(usuarioProvider::convertToDtoUsuario).collect(Collectors.toList());
	}

	@PostMapping("/usuario/add")
	public UsuarioDto anadirUsuario(@RequestBody @Valid UsuarioDto usuario) {
		UsuarioEntity uEnt = this.usuarioProvider.anadirUsuario(usuarioProvider.convertToEntityUsuario(usuario));
		return usuarioProvider.convertToDtoUsuario(uEnt);
	}

	@GetMapping("/usuario/getById/{id}")
	public UsuarioDto buscarUsuarioId(@PathVariable("id") int usuarioId) {
		return usuarioProvider.convertToDtoUsuario(this.usuarioProvider.buscarUsuarioId(usuarioId));
	}

	@PutMapping("/usuario/editar/{id}")
	public UsuarioDto editarUsuario(@RequestBody @Valid UsuarioDto usuario,@PathVariable("id") int usuarioId) {
		UsuarioEntity uEnt = this.usuarioProvider.editarUsuario(usuarioProvider.convertToEntityUsuario(usuario), usuarioId);
		return usuarioProvider.convertToDtoUsuario(uEnt);
	}

	@DeleteMapping("/usuario/delete/{id}")
	public String deleteUsuarioById(@PathVariable("id") int usuarioId) {
		this.usuarioProvider.deleteUsuarioById(usuarioId);
		return "Eliminado correctamente";
	}

	@GetMapping("/prestamo/usuario/{id}")
	public List<PrestamoDto> listarPrestamosUsuario(@PathVariable("id") int usuarioId){
		List<PrestamoEntity> listaAux = this.usuarioProvider.listarPrestamosUsuario(usuarioId);
		return listaAux.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList());
	
	}
}
