package com.practicas.libreriabk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.practicas.libreriabk.dto.UsuarioDto;
import com.practicas.libreriabk.provider.UsuarioProvider;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UsusarioControllerTest {
	
	@InjectMocks
    private UsuarioController usuarioController;
	
	@Mock
    private UsuarioProvider usuarioProvider;
	
	@Test 
	void test_listarUsuarios() {
		
		UsuarioDto usuario = UsuarioDto.builder()
										.id(1)
										.dni("11222111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		UsuarioDto usuario2 = UsuarioDto.builder()
										.id(2)
										.dni("11233111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();

		List<UsuarioDto> listaUsuarios = new ArrayList<UsuarioDto>();
		listaUsuarios.add(usuario);
		listaUsuarios.add(usuario2);
		Mockito.when(usuarioProvider.listarUsuarios()).thenReturn(listaUsuarios);

		ResponseEntity<List<UsuarioDto>> response = usuarioController.listarUsuarios();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaUsuarios.size(), response.getBody().size());
	}
	
	@Test 
	void test_usuario_add() {
		UsuarioDto usuario2 = UsuarioDto.builder()
										.id(2)
										.dni("11233111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		Mockito.when(usuarioProvider.anadirUsuario(usuario2)).thenReturn(usuario2);

		ResponseEntity<UsuarioDto> response = usuarioController.anadirUsuario(usuario2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(usuario2, response.getBody());
	}
	
	@Test 
	void test_usuario_add_malFormado() {
		UsuarioDto usuario = UsuarioDto.builder()
										.id(1)
										.dni("11222111Z")
										.nombre(null)
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		
		Mockito.when(usuarioProvider.anadirUsuario(usuario)).thenReturn(null);

		ResponseEntity<UsuarioDto> response = usuarioController.anadirUsuario(usuario);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_usuario_getById() {
		int id = 2;
		UsuarioDto usuario = UsuarioDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();		
		
		Mockito.when(usuarioProvider.buscarUsuarioId(id)).thenReturn(usuario);

		ResponseEntity<UsuarioDto> response = usuarioController.buscarUsuarioId(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(usuario, response.getBody());
	}
	
	@Test 
	void test_usuario_getById_idInexistente() {
		int id = 100;
		Mockito.when(usuarioProvider.buscarUsuarioId(id)).thenReturn(null);
		ResponseEntity<UsuarioDto> response = usuarioController.buscarUsuarioId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_usuario_editar() {
		UsuarioDto usuario = UsuarioDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();
		Mockito.when(usuarioProvider.editarUsuario(usuario,2)).thenReturn(usuario);

		ResponseEntity<UsuarioDto> response = usuarioController.editarUsuario(usuario,2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(usuario, response.getBody());
	}
	
	@Test 
	void test_usuario_editar_error() {
		UsuarioDto usuario = UsuarioDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre(null)
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();
		Mockito.when(usuarioProvider.editarUsuario(usuario,2)).thenReturn(null);

		ResponseEntity<UsuarioDto> response = usuarioController.editarUsuario(usuario,2);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_usuario_eliminar() {

		ResponseEntity<String> response = usuarioController.deleteUsuarioById(70);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Eliminado correctamente", response.getBody());
	}
	
	@Test 
	void test_usuario_eliminarLogico() {
		UsuarioDto usuario = UsuarioDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(false)
				.build();
		Mockito.when(usuarioProvider.logicDeleteUsuarioById(2)).thenReturn(usuario);

		ResponseEntity<UsuarioDto> response = usuarioController.logicDeleteUsuarioById(2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(usuario, response.getBody());
	}
	
	@Test 
	void test_usuario_eliminarLogico_error() {
		
		Mockito.when(usuarioProvider.logicDeleteUsuarioById(100)).thenReturn(null);

		ResponseEntity<UsuarioDto> response = usuarioController.logicDeleteUsuarioById(100);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}


}
