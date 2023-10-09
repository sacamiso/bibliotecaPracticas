package com.practicas.libreriabk.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.practicas.libreriabk.dto.CategoriaDto;
import com.practicas.libreriabk.provider.CategoriaProvider;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.repository.CategoriaRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {
	
	@InjectMocks
    private CategoriaController categoriaController;
	
	@Spy
    private CategoriaProvider categoriaProvider;
	
	@Spy
	private CategoriaRepository categoriaRepository;
	
	@Spy
	private LibroProvider libroProvider;
	
	@Spy
	private ModelMapper modelMapper;
	
	
	
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

}
