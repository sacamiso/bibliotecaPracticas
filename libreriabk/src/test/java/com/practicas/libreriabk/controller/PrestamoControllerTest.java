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
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.provider.PrestamoProvider;
import com.practicas.libreriabk.provider.UsuarioProvider;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrestamoControllerTest {

	@InjectMocks
	private PrestamoController prestamoController;

	@Mock
	private PrestamoProvider prestamoProvider;
	
	@Mock
	private UsuarioProvider usuarioProvider;

	@Mock
	private LibroProvider libroProvider;
	
	@Test
	void test_listarPrestamos() {

		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();

		List<PrestamoDto> listaPrestamos = new ArrayList<PrestamoDto>();
		listaPrestamos.add(prestamo);

		Mockito.when(prestamoProvider.listarPrestamos()).thenReturn(listaPrestamos);

		ResponseEntity<List<PrestamoDto>> response = prestamoController.listarPrestamos();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(listaPrestamos.size(), response.getBody().size());
	}

	@Test
	void test_prestamo_add() {
		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();
		
		Mockito.when(prestamoProvider.anadirPrestamo(prestamo)).thenReturn(prestamo);

		ResponseEntity<PrestamoDto> response = prestamoController.anadirPrestamo(prestamo);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(prestamo, response.getBody());
	}

	@Test
	void test_pretamo_add_malFormado() {

		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo(null)
				.idUsuario(1).libros(listaLibros).build();
		
		Mockito.when(prestamoProvider.anadirPrestamo(prestamo)).thenReturn(null);

		ResponseEntity<PrestamoDto> response = prestamoController.anadirPrestamo(prestamo);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_prestamo_getById() {
		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();		
		
		Mockito.when(prestamoProvider.buscarPrestamoId(1)).thenReturn(prestamo);

		ResponseEntity<PrestamoDto> response = prestamoController.buscarPrestamoId(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(prestamo, response.getBody());
	}
	
	@Test 
	void test_prestamo_getById_idInexistente() {
		int id = 100;
		Mockito.when(prestamoProvider.buscarPrestamoId(id)).thenReturn(null);
		ResponseEntity<PrestamoDto> response = prestamoController.buscarPrestamoId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_prestamo_editar() {
		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();		
		
		Mockito.when(prestamoProvider.editarPrestamo(prestamo,1)).thenReturn(prestamo);

		ResponseEntity<PrestamoDto> response = prestamoController.editarPrestamo(prestamo,1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(prestamo, response.getBody());
	}
	
	@Test 
	void test_prestamo_editar_error() {
		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo(null)
				.idUsuario(1).libros(listaLibros).build();		
		
		
		Mockito.when(prestamoProvider.editarPrestamo(prestamo,1)).thenReturn(null);

		ResponseEntity<PrestamoDto> response = prestamoController.editarPrestamo(prestamo,1);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_prestamo_eliminar() {
		ResponseEntity<String> response = prestamoController.deletePrestamoById(70);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Eliminado correctamente", response.getBody());
	}
	
	@Test
	void test_listarPrestamos_usuarioID() {

		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();

		List<PrestamoDto> listaPrestamos = new ArrayList<PrestamoDto>();
		listaPrestamos.add(prestamo);

		Mockito.when(usuarioProvider.listarPrestamosUsuario(1)).thenReturn(listaPrestamos);

		ResponseEntity<List<PrestamoDto>> response = prestamoController.listarPrestamosUsuario(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(listaPrestamos.size(), response.getBody().size());
	}
	
	@Test
	void test_listarPrestamos_usuarioID_inexistente() {

		Mockito.when(usuarioProvider.listarPrestamosUsuario(100)).thenReturn(null);

		ResponseEntity<List<PrestamoDto>> response = prestamoController.listarPrestamosUsuario(100);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());
	}
	
	@Test
	void test_listarPrestamos_libroID() {

		LibroDto libro = LibroDto.builder().idLibro(1).titulo("libro").edicion(9).idAutor(2).idCategoria(7).build();

		List<LibroDto> listaLibros = new ArrayList<LibroDto>();
		listaLibros.add(libro);
		listaLibros.add(libro);

		PrestamoDto prestamo = PrestamoDto.builder().idPrestamo(1).devolucion("10/02/2018").prestamo("10/01/2018")
				.idUsuario(1).libros(listaLibros).build();

		List<PrestamoDto> listaPrestamos = new ArrayList<PrestamoDto>();
		listaPrestamos.add(prestamo);

		Mockito.when(libroProvider.listarPrestamosLibro(1)).thenReturn(listaPrestamos);

		ResponseEntity<List<PrestamoDto>> response = prestamoController.listarPrestamosLibro(1);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(listaPrestamos.size(), response.getBody().size());
	}
	
	@Test
	void test_listarPrestamos_libroID_inexistente() {

		Mockito.when(libroProvider.listarPrestamosLibro(100)).thenReturn(null);

		ResponseEntity<List<PrestamoDto>> response = prestamoController.listarPrestamosLibro(100);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(null, response.getBody());
	}
	
	
}
