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

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.provider.AutorProvider;
import com.practicas.libreriabk.provider.CategoriaProvider;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.provider.PrestamoProvider;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LibroControllerTest {
	
	@InjectMocks
	private LibroController libroController;
	
	@Mock
	private LibroProvider libroProvider;
	
	@Mock
	private AutorProvider autorProvider;
	
	@Mock
	private CategoriaProvider categoriaProvider;
	
	@Mock
	private PrestamoProvider prestamoProvider;
	
	@Test 
	void test_listarLibros() {
		
		LibroDto libro = LibroDto.builder()
										.idLibro(1)
										.titulo("libro")
										.edicion(9)
										.idAutor(2)
										.idCategoria(7)
										.build();
		
		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);
		Mockito.when(libroProvider.listarLibros()).thenReturn(listaLibros);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibros();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaLibros.size(), response.getBody().size());
	}
	
	@Test 
	void test_libro_add() {
		LibroDto libro = LibroDto.builder()
				.idLibro(1)
				.titulo("libro")
				.edicion(9)
				.idAutor(2)
				.idCategoria(7)
				.build();
		Mockito.when(libroProvider.anadirLibro(libro)).thenReturn(libro);

		ResponseEntity<LibroDto> response = libroController.anadirLibro(libro);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(libro, response.getBody());
	}
	
	@Test 
	void test_libro_add_malFormado() {
		LibroDto libro = LibroDto.builder()
				.idLibro(1)
				.titulo("libro")
				.edicion(9)
				.idAutor(122)
				.idCategoria(723)
				.build();
		
		Mockito.when(libroProvider.anadirLibro(libro)).thenReturn(null);

		ResponseEntity<LibroDto> response = libroController.anadirLibro(libro);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_libro_getById() {
		int id = 2;
		LibroDto libro = LibroDto.builder()
				.idLibro(2)
				.titulo("libro")
				.edicion(9)
				.idAutor(2)
				.idCategoria(7)
				.build();		
		
		Mockito.when(libroProvider.buscarLibroId(id)).thenReturn(libro);

		ResponseEntity<LibroDto> response = libroController.buscarLibroId(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(libro, response.getBody());
	}
	
	@Test 
	void test_libro_getById_idInexistente() {
		int id = 100;
		Mockito.when(libroProvider.buscarLibroId(id)).thenReturn(null);
		ResponseEntity<LibroDto> response = libroController.buscarLibroId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_libro_editar() {
		LibroDto libro = LibroDto.builder()
				.idLibro(1)
				.titulo("libro")
				.edicion(9)
				.idAutor(2)
				.idCategoria(7)
				.build();
		
		Mockito.when(libroProvider.editarLibro(libro,2)).thenReturn(libro);

		ResponseEntity<LibroDto> response = libroController.editarLibro(libro,2);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(libro, response.getBody());
	}
	
	@Test 
	void test_libro_editar_error() {
		LibroDto libro = LibroDto.builder()
				.idLibro(2)
				.titulo("libro")
				.edicion(9)
				.idAutor(122)
				.idCategoria(723)
				.build();
		
		Mockito.when(libroProvider.editarLibro(libro,2)).thenReturn(null);

		ResponseEntity<LibroDto> response = libroController.editarLibro(libro,2);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_libro_eliminar() {
		ResponseEntity<String> response = libroController.deleteLibroById(70);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Eliminado correctamente", response.getBody());
	}
	
	@Test 
	void test_listarLibros_AutorID() {
		
		LibroDto libro = LibroDto.builder()
										.idLibro(1)
										.titulo("libro")
										.edicion(9)
										.idAutor(2)
										.idCategoria(7)
										.build();
		
		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);
		Mockito.when(autorProvider.listarLibrosAutor(1)).thenReturn(listaLibros);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosAutor(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaLibros.size(), response.getBody().size());
	}
	
	@Test 
	void test_listarLibros_AutorID_inexistente() {
		
		Mockito.when(autorProvider.listarLibrosAutor(1000)).thenReturn(null);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosAutor(1000);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_listarLibros_CategoriaID() {
		
		LibroDto libro = LibroDto.builder()
										.idLibro(1)
										.titulo("libro")
										.edicion(9)
										.idAutor(2)
										.idCategoria(7)
										.build();
		
		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);
		Mockito.when(categoriaProvider.listarLibrosCategoria(1)).thenReturn(listaLibros);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosCategoria(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaLibros.size(), response.getBody().size());
	}
	
	@Test 
	void test_listarLibros_categoriaID_inexistente() {
		
		Mockito.when(categoriaProvider.listarLibrosCategoria(1000)).thenReturn(null);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosCategoria(1000);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_listarLibros_PrestamoID() {
		
		LibroDto libro = LibroDto.builder()
										.idLibro(1)
										.titulo("libro")
										.edicion(9)
										.idAutor(2)
										.idCategoria(7)
										.build();
		
		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);
		Mockito.when(prestamoProvider.listarLibrosPrestamo(1)).thenReturn(listaLibros);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosPrestamo(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaLibros.size(), response.getBody().size());
	}
	
	@Test 
	void test_listarLibros_prestamoID_inexistente() {
		
		Mockito.when(prestamoProvider.listarLibrosPrestamo(1000)).thenReturn(null);

		ResponseEntity<List<LibroDto>> response = libroController.listarLibrosPrestamo(1000);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}

}
