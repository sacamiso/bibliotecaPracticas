package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.LibroDto;
import dto.PrestamoDto;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.PrestamoProvider;

@RestController
public class PrestamoController {

	@Autowired
	private PrestamoProvider prestamoProvider;

	@Autowired
	private ModelMapper modelMapper;

	// Dudas
	private PrestamoDto convertToDtoPrestamo(PrestamoEntity presE) {
		return modelMapper.map(presE, PrestamoDto.class);
	}

	// Dudas
	private PrestamoEntity convertToEntityPrestamo(PrestamoDto presDto) {
		return modelMapper.map(presDto, PrestamoEntity.class);
	}
	
	private LibroDto convertToDtoLibro(LibroEntity liE) {
		return modelMapper.map(liE, LibroDto.class);
	}

	@GetMapping("/prestamo/all")
	public List<PrestamoDto> listarPrestamos(){
		List<PrestamoEntity> pEnt = this.prestamoProvider.listarPrestamos();
		return pEnt.stream().map(this::convertToDtoPrestamo).collect(Collectors.toList());
	}

	@PostMapping("/prestamo/add")
	public PrestamoDto anadirPrestamo(@RequestBody @Valid PrestamoDto prestamo) {
		PrestamoEntity pEnt = this.prestamoProvider.anadirPrestamo(this.convertToEntityPrestamo(prestamo));
		return this.convertToDtoPrestamo(pEnt);
	}

	@GetMapping("/prestamo/getById/{id}")
	public PrestamoDto buscarPrestamoId(@PathVariable("id") int prestamoId) {
		return this.convertToDtoPrestamo(this.prestamoProvider.buscarPrestamoId(prestamoId));
	}

	@PutMapping("/prestamo/editar/{id}")
	public PrestamoDto editarPrestamo(@RequestBody @Valid PrestamoDto prestamo,@PathVariable("id") int prestamoId) {
		PrestamoEntity pEnt = this.prestamoProvider.editarPrestamo(this.convertToEntityPrestamo(prestamo), prestamoId);
		return this.convertToDtoPrestamo(pEnt);
	}

	@DeleteMapping("/prestamo/delete/{id}")
	String deletePrestamoById(@PathVariable("id") int prestamoId) {
		this.prestamoProvider.deletePrestamoById(prestamoId);
		return "Eliminado correctamente";
	}

	@GetMapping("/libro/prestamo/{id}")
	public List<LibroDto> listarLibrosPrestamo(@PathVariable("id") int prestamoId){
		List<LibroEntity> listaAux = this.prestamoProvider.listarLibrosPrestamo(prestamoId);
		return listaAux.stream().map(this::convertToDtoLibro).collect(Collectors.toList());
	}

}
