package com.practicas.libreriabk.controller;

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

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.entity.PrestamoEntity;
import com.practicas.libreriabk.provider.LibroProvider;
import com.practicas.libreriabk.provider.PrestamoProvider;

@RestController
public class PrestamoController {

	@Autowired
	private PrestamoProvider prestamoProvider;

	@Autowired
	private LibroProvider libroProvider;

	@GetMapping("/prestamo/all")
	public ResponseEntity<List<PrestamoDto>> listarPrestamos(){
		List<PrestamoEntity> pEnt = this.prestamoProvider.listarPrestamos();
		return new ResponseEntity<List<PrestamoDto>>(pEnt.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList()),HttpStatus.OK);
	}

	@PostMapping("/prestamo/add")
	public ResponseEntity<PrestamoDto> anadirPrestamo(@RequestBody @Valid PrestamoDto prestamo) {
		PrestamoEntity pEnt = this.prestamoProvider.anadirPrestamo(prestamoProvider.convertToEntityPrestamo(prestamo));
		if(pEnt ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PrestamoDto>(prestamoProvider.convertToDtoPrestamo(pEnt),HttpStatus.OK);
	}

	@GetMapping("/prestamo/getById/{id}")
	public ResponseEntity<PrestamoDto> buscarPrestamoId(@PathVariable("id") int prestamoId) {
		PrestamoDto p = prestamoProvider.convertToDtoPrestamo(this.prestamoProvider.buscarPrestamoId(prestamoId));
		if(p ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PrestamoDto>(p,HttpStatus.OK);
	}

	@PutMapping("/prestamo/editar/{id}")
	public ResponseEntity<PrestamoDto> editarPrestamo(@RequestBody @Valid PrestamoDto prestamo,@PathVariable("id") int prestamoId) {
		PrestamoEntity pEnt = this.prestamoProvider.editarPrestamo(prestamoProvider.convertToEntityPrestamo(prestamo), prestamoId);
		if(pEnt ==  null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PrestamoDto>(prestamoProvider.convertToDtoPrestamo(pEnt),HttpStatus.OK);
	}

	@DeleteMapping("/prestamo/delete/{id}")
	public ResponseEntity<String> deletePrestamoById(@PathVariable("id") int prestamoId) {
		this.prestamoProvider.deletePrestamoById(prestamoId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/libro/prestamo/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosPrestamo(@PathVariable("id") int prestamoId){
		List<LibroEntity> listaAux = this.prestamoProvider.listarLibrosPrestamo(prestamoId);
		if(listaAux==null) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(listaAux.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList()),HttpStatus.OK);
	}

}
