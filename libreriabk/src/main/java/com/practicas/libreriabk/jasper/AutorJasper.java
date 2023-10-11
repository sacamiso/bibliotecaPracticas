package com.practicas.libreriabk.jasper;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practicas.libreriabk.dto.AutorDto;
import com.practicas.libreriabk.entity.AutorEntity;
import com.practicas.libreriabk.provider.AutorProvider;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class AutorJasper {
	
	@Autowired
	AutorProvider autorProvider;
	
	public void generarPDF() {
	    try {
	        // Cargo el archivo compilado JasperReport
	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/jasper/ReporteEmpleados.jasper");

	        // Puedes preparar tus datos en una lista o un arreglo
	        List<AutorDto> listaAutorDto = this.autorProvider.listarAutores();
	        List<AutorEntity> listaEmpleados = listaAutorDto.stream().map(this.autorProvider::convertToEntityAutor).collect(Collectors.toList());

	        // Crear un objeto JRBeanArrayDataSource con la lista de datos
	        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(listaEmpleados.toArray());

	        // Puedes pasar parámetros si los tienes en tu informe
	        Map<String, Object> parametros = new HashMap<>();
	        parametros.put("logoEmpresa", new FileInputStream("src/main/resources/images/biblioteca.png"));
	        parametros.put("imagenAlternativa", new FileInputStream("src/main/resources/images/hiberus.jpg"));
	        parametros.put("ds", dataSource);

	        // Llenar el informe con datos y parámetros
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

	        // Exportar el informe a un archivo PDF
	        JasperExportManager.exportReportToPdfFile(jasperPrint, "reportAutor.pdf"); 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
