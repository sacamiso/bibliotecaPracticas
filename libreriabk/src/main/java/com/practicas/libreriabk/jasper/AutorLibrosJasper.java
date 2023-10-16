package com.practicas.libreriabk.jasper;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.entity.AutorEntity;
import com.practicas.libreriabk.entity.LibroEntity;
import com.practicas.libreriabk.provider.AutorProvider;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class AutorLibrosJasper {
	
	@Autowired
	AutorProvider autorProvider;
	
	
	public void generarPDF() {
	    try {
	        // Cargo el archivo compilado JasperReport
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/jasper/AutoresLibro.jasper");

	        // Puedes preparar tus datos en una lista o un arreglo
	        List<AutorDto> listaAutorDto = this.autorProvider.listarAutores();
	        List<AutorEntity> listaEmpleados = listaAutorDto.stream().map(this.autorProvider::convertToEntityAutor).collect(Collectors.toList());

	       

	        // Puedes pasar parámetros si los tienes en tu informe
	        Map<String, Object> parametros = new HashMap<>();
	        parametros.put("logoEmpresa", new FileInputStream("src/main/resources/images/biblioteca.png"));
	        parametros.put("imagenAlternativa", new FileInputStream("src/main/resources/images/hiberus.jpg"));

	        List<Hashtable<String, Object>> listadoAutores = new ArrayList<>();
	        for (AutorEntity autor :listaEmpleados) {
	        	Hashtable<String, Object> hash = new Hashtable<>();
	        	hash.put("id", autor.getId());
	        	hash.put("nombreCompleto", autor.getNombreCompleto());
	        	hash.put("telefono", autor.getTelefono());
	        	hash.put("activo", autor.getActivoString());
	        	hash.put("email", autor.getEmail());
	        	hash.put("dni", autor.getDni());
	        	List<Hashtable<String, Object>> listaLibros = new ArrayList<>();
	        	for (LibroEntity libro:autor.getListaLibros()) {
	        		Hashtable<String, Object> hashLibro = new Hashtable<>();
	        		hashLibro.put("titulo", libro.getTitulo());
	        		hashLibro.put("idLibro", libro.getIdLibro());
	        		hashLibro.put("edicion", libro.getEdicion());
	        		hashLibro.put("idCategoria", libro.getIdCategoria());
	        		listaLibros.add(hashLibro);
	        	}
	        	hash.put("listaLibros", listaLibros); 
	        	listadoAutores.add(hash);
	        	
	        }
	        parametros.put("listaAutores", listadoAutores);
	        
	        
	        // Llenar el informe con datos y parámetros
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

	        // Exportar el informe a un archivo PDF
	        JasperExportManager.exportReportToPdfFile(jasperPrint, "reportAutorLibros.pdf"); 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
