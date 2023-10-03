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

import dto.LibroDto;
import dto.PrestamoDto;
import entity.LibroEntity;
import entity.PrestamoEntity;
import provider.LibroProvider;
import provider.PrestamoProvider;

@RestController
public class PrestamoController {

	@Autowired
	private PrestamoProvider prestamoProvider;

	@Autowired
	private LibroProvider libroProvider;


	@GetMapping("/prestamo/all")
	public List<PrestamoDto> listarPrestamos(){
		List<PrestamoEntity> pEnt = this.prestamoProvider.listarPrestamos();
		return pEnt.stream().map(prestamoProvider::convertToDtoPrestamo).collect(Collectors.toList());
	}

	@PostMapping("/prestamo/add")
	public PrestamoDto anadirPrestamo(@RequestBody @Valid PrestamoDto prestamo) {
		PrestamoEntity pEnt = this.prestamoProvider.anadirPrestamo(prestamoProvider.convertToEntityPrestamo(prestamo));
		return prestamoProvider.convertToDtoPrestamo(pEnt);
	}

	@GetMapping("/prestamo/getById/{id}")
	public PrestamoDto buscarPrestamoId(@PathVariable("id") int prestamoId) {
		return prestamoProvider.convertToDtoPrestamo(this.prestamoProvider.buscarPrestamoId(prestamoId));
	}

	@PutMapping("/prestamo/editar/{id}")
	public PrestamoDto editarPrestamo(@RequestBody @Valid PrestamoDto prestamo,@PathVariable("id") int prestamoId) {
		PrestamoEntity pEnt = this.prestamoProvider.editarPrestamo(prestamoProvider.convertToEntityPrestamo(prestamo), prestamoId);
		return prestamoProvider.convertToDtoPrestamo(pEnt);
	}

	@DeleteMapping("/prestamo/delete/{id}")
	String deletePrestamoById(@PathVariable("id") int prestamoId) {
		this.prestamoProvider.deletePrestamoById(prestamoId);
		return "Eliminado correctamente";
	}

	@GetMapping("/libro/prestamo/{id}")
	public List<LibroDto> listarLibrosPrestamo(@PathVariable("id") int prestamoId){
		List<LibroEntity> listaAux = this.prestamoProvider.listarLibrosPrestamo(prestamoId);
		return listaAux.stream().map(libroProvider::convertToDtoLibro).collect(Collectors.toList());
	}

}
