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

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.provider.CategoriaProvider;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {
	
	@InjectMocks
    private CategoriaController categoriaController;
	
	@Mock
    private CategoriaProvider categoriaProvider;
	
	@Test 
	void test_categoria_getById() {
		int id = 7;
		CategoriaDto categoria = CategoriaDto.builder().id(id).nombre("prueba").descripcion("prueba").build();
		Mockito.when(categoriaProvider.buscarCategoriaId(id)).thenReturn(categoria);

		ResponseEntity<CategoriaDto> response = categoriaController.buscarCategoriaId(id);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(categoria, response.getBody());
	}
	
	@Test 
	void test_categoria_getById_nulls() {
		int id = 100;
		Mockito.when(categoriaProvider.buscarCategoriaId(id)).thenReturn(null);
		ResponseEntity<CategoriaDto> response = categoriaController.buscarCategoriaId(id);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_listarCategorias() {
		
		CategoriaDto categoria = CategoriaDto.builder().id(1).nombre("prueba").descripcion("prueba").build();
		CategoriaDto categoria1 = CategoriaDto.builder().id(2).nombre("prueba2").descripcion("prueba2").build();

		List<CategoriaDto> listaCategorias = new ArrayList<CategoriaDto>();
		listaCategorias.add(categoria);
		listaCategorias.add(categoria1);
		Mockito.when(categoriaProvider.listarCategorias()).thenReturn(listaCategorias);

		ResponseEntity<List<CategoriaDto>> response = categoriaController.listarCategorias();
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(listaCategorias.size(), response.getBody().size());
	}
	
	@Test 
	void test_categoria_add() {
		CategoriaDto categoria = CategoriaDto.builder().id(10).nombre("nuevaCategoria").descripcion("nuevaCategoria").build();
		Mockito.when(categoriaProvider.anadirCategoria(categoria)).thenReturn(categoria);

		ResponseEntity<CategoriaDto> response = categoriaController.anadirCategoria(categoria);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(categoria, response.getBody());
	}
	
	@Test 
	void test_categoria_add_malFormada() {
		CategoriaDto categoria = CategoriaDto.builder().id(7).nombre(null).descripcion("nuevaCategoria").build();
		Mockito.when(categoriaProvider.anadirCategoria(categoria)).thenReturn(null);

		ResponseEntity<CategoriaDto> response = categoriaController.anadirCategoria(categoria);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_categoria_editar() {
		CategoriaDto categoria = CategoriaDto.builder().id(7).nombre("pruebaModificada").descripcion("pruebaModificada").build();
		Mockito.when(categoriaProvider.editarCategoria(categoria,7)).thenReturn(categoria);

		ResponseEntity<CategoriaDto> response = categoriaController.editarCategoria(categoria,7);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals(categoria, response.getBody());
	}
	
	@Test 
	void test_categoria_editar_error() {
		CategoriaDto categoria = CategoriaDto.builder().id(7).nombre(null).descripcion("pruebaModificada con errores").build();
		Mockito.when(categoriaProvider.editarCategoria(categoria,7)).thenReturn(null);

		ResponseEntity<CategoriaDto> response = categoriaController.editarCategoria(categoria,7);
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	    assertEquals(null, response.getBody());
	}
	
	@Test 
	void test_categoria_eliminar() {

		ResponseEntity<String> response = categoriaController.deleteCategoriaById(70);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	    assertEquals("Eliminado correctamente", response.getBody());
	}

}
