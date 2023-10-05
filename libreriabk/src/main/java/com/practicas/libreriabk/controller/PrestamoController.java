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

import com.practicas.libreriabk.dto.LibroDto;
import com.practicas.libreriabk.dto.PrestamoDto;
import com.practicas.libreriabk.provider.PrestamoProvider;

@RestController
public class PrestamoController {

	@Autowired
	private PrestamoProvider prestamoProvider;

	@GetMapping("/prestamo/all")
	public ResponseEntity<List<PrestamoDto>> listarPrestamos() {
		List<PrestamoDto> listaprestamosE = this.prestamoProvider.listarPrestamos();
		return new ResponseEntity<List<PrestamoDto>>(listaprestamosE, HttpStatus.OK);
	}

	@PostMapping("/prestamo/add")
	public ResponseEntity<PrestamoDto> anadirPrestamo(@RequestBody @Valid PrestamoDto prestamo) {
		PrestamoDto pEnt = this.prestamoProvider.anadirPrestamo(prestamo);

		if (pEnt == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<PrestamoDto>(pEnt, HttpStatus.OK);
	}

	@GetMapping("/prestamo/getById/{id}")
	public ResponseEntity<PrestamoDto> buscarPrestamoId(@PathVariable("id") int prestamoId) {
		PrestamoDto p = this.prestamoProvider.buscarPrestamoId(prestamoId);
		if (p == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PrestamoDto>(p, HttpStatus.OK);
	}

	@PutMapping("/prestamo/editar/{id}")
	public ResponseEntity<PrestamoDto> editarPrestamo(@RequestBody @Valid PrestamoDto prestamo,
			@PathVariable("id") int prestamoId) {
		PrestamoDto pDto = this.prestamoProvider.editarPrestamo(prestamo,prestamoId);
		if (pDto == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PrestamoDto>(pDto, HttpStatus.OK);
	}

	@DeleteMapping("/prestamo/delete/{id}")
	public ResponseEntity<String> deletePrestamoById(@PathVariable("id") int prestamoId) {
		this.prestamoProvider.deletePrestamoById(prestamoId);
		return new ResponseEntity<String>("Eliminado correctamente", HttpStatus.OK);
	}

	@GetMapping("/libro/prestamo/{id}")
	public ResponseEntity<List<LibroDto>> listarLibrosPrestamo(@PathVariable("id") int prestamoId) {
		List<LibroDto> librosEn = this.prestamoProvider.listarLibrosPrestamo(prestamoId);
		if (librosEn == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<LibroDto>>(librosEn, HttpStatus.OK);
	}

}
