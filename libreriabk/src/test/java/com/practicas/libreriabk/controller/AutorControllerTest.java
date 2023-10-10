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

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.provider.AutorProvider;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AutorControllerTest {
	
	@InjectMocks
    private AutorController autorController;
	
	@Mock
    private AutorProvider autorProvider;
	
	@Test 
	void test_listarAutores() {
		
		AutorDto autor = AutorDto.builder()
										.id(1)
										.dni("11222111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		AutorDto autor2 = AutorDto.builder()
										.id(2)
										.dni("11233111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();

		List<AutorDto> listaAutores = new ArrayList<AutorDto>();
		listaAutores.add(autor);
		listaAutores.add(autor2);
		Mockito.when(autorProvider.listarAutores()).thenReturn(listaAutores);

		ResponseEntity<List<AutorDto>> response = autorController.listarAutores();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaAutores.size(), response.getBody().size());
	}
	
	@Test 
	void test_autor_add() {
		AutorDto autor2 = AutorDto.builder()
										.id(2)
										.dni("11233111Z")
										.nombre("sara")
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		Mockito.when(autorProvider.anadirAutor(autor2)).thenReturn(autor2);

		ResponseEntity<AutorDto> response = autorController.anadirAutor(autor2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(autor2, response.getBody());
	}
	
	@Test 
	void test_autor_add_malFormado() {
		AutorDto autor = AutorDto.builder()
										.id(1)
										.dni("11222111Z")
										.nombre(null)
										.apellido1("camison")
										.apellido2("peraita")
										.telefono(777666776)
										.email("sara@gmail.com")
										.activo(true)
										.build();
		
		Mockito.when(autorProvider.anadirAutor(autor)).thenReturn(null);

		ResponseEntity<AutorDto> response = autorController.anadirAutor(autor);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_autor_getById() {
		int id = 2;
		AutorDto autor = AutorDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();		
		
		Mockito.when(autorProvider.buscarAutorId(id)).thenReturn(autor);

		ResponseEntity<AutorDto> response = autorController.buscarAutorId(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(autor, response.getBody());
	}
	
	@Test 
	void test_autor_getById_idInexistente() {
		int id = 100;
		Mockito.when(autorProvider.buscarAutorId(id)).thenReturn(null);
		ResponseEntity<AutorDto> response = autorController.buscarAutorId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_autor_editar() {
		AutorDto autor = AutorDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();
		Mockito.when(autorProvider.editarAutor(autor,2)).thenReturn(autor);

		ResponseEntity<AutorDto> response = autorController.editarAutor(autor,2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(autor, response.getBody());
	}
	
	@Test 
	void test_autor_editar_error() {
		AutorDto autor = AutorDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre(null)
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(true)
				.build();
		Mockito.when(autorProvider.editarAutor(autor,2)).thenReturn(null);

		ResponseEntity<AutorDto> response = autorController.editarAutor(autor,2);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_autor_eliminar() {

		ResponseEntity<String> response = autorController.deleteAutorById(70);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Eliminado correctamente", response.getBody());
	}
	
	@Test 
	void test_autor_eliminarLogico() {
		AutorDto autor = AutorDto.builder()
				.id(2)
				.dni("11233111Z")
				.nombre("sara")
				.apellido1("camison")
				.apellido2("peraita")
				.telefono(777666776)
				.email("sara@gmail.com")
				.activo(false)
				.build();
		Mockito.when(autorProvider.logicDeleteAutorById(2)).thenReturn(autor);

		ResponseEntity<AutorDto> response = autorController.logicDeleteAutorById(2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(autor, response.getBody());
	}
	
	@Test 
	void test_autor_eliminarLogico_error() {
		
		Mockito.when(autorProvider.logicDeleteAutorById(100)).thenReturn(null);

		ResponseEntity<AutorDto> response = autorController.logicDeleteAutorById(100);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}

}
