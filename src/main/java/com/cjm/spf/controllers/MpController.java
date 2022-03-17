package com.cjm.spf.controllers;

import org.springframework.stereotype.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjm.spf.domain.*;
import com.cjm.spf.servicio.*;




@Controller
@Slf4j
public class MpController {
	
	@Autowired
	RegistroService registroService;
	
	@Autowired
	CarpetaService carpetasService;
	
	@Autowired
	ArchivosMPService archivosService;
	
	@Autowired
	AgresorService agresorService;
	
	@Autowired
	ExpedienteService expedienteService;
	
	@Autowired
	RedApoyoService apoyoService;
	
	@Autowired
	RFamiliaresService familiaresService;
	
	@Autowired
	DomAgresorService domagresorService;
	
	@Autowired
	FijacionAService fijacionService;
	
	@GetMapping("/perfil_mp/{id}")
	public String perfilMP(Registro registro, Model model) {
		List<ArchivosMP> archivos = archivosService.encontrarArchivos(registro.getId());
		
		registro = registroService.encontrarRegistro(registro);
		CarpetasMP ultimaCarpeta = carpetasService.ultimaCarpeta(registro.getId());
		CarpetasMP nuevaCarpeta = new CarpetasMP();
		nuevaCarpeta.setUsuaria(registro.getId());
		LocalDate now = LocalDate.now();
		if(archivos.isEmpty()) archivos = null;
		
		System.out.println(archivos);
		model.addAttribute("archivos", archivos);
		model.addAttribute("now", now);
		model.addAttribute("ultima", ultimaCarpeta);
		model.addAttribute("registro", registro);
		model.addAttribute("titulo", "Perfil de la usuaria");
		model.addAttribute("nueva", nuevaCarpeta);
		return "PerfilPsic";
		
	}
	
	@GetMapping("/informe_mp")														//Obtener informe de empoderamiento
	public String informeMensual( Model model) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		Long carpetas = carpetasService.findUsuariasPrimeraVez(mes, anio);
		Long archivos = archivosService.encontrarPorMesyAnio(mes, anio);
		model.addAttribute("carpetas", carpetas);
		model.addAttribute("archivos", archivos);
		String titulo = "Informe mensual";
		model.addAttribute("titulo", titulo);
		return "infMenTS";
	}
	
	@GetMapping("/registrar_archivo/{id}")
	public String registrarArchivos(Registro registro, ArchivosMP archivo, Model model) {
		archivo.setUsuaria(registro.getId());
		
		model.addAttribute("titulo", "Registro de archivos");
		model.addAttribute("archivo", archivo);
		return "archivos_mp";
		
	}
	
	@GetMapping("/ver_redes/{id}")																	//VER RED DE APOYO
	public String verRedes(Registro registro, Model model) {
		Expediente exp = expedienteService.encontrarPorId(registro.getId());
		List<RedApoyo> redes = apoyoService.findRegistros(exp.getExp());
		model.addAttribute("redes", redes);
		return "archivos_mp";
		
	}
	
	@GetMapping("/ver_familiograma/{id}")															//VER FAMILIOGRAMA
	public String verFamiliograma(Registro registro, Model model) {
		Expediente exp = expedienteService.encontrarPorId(registro.getId());
		List<RFamiliares> familiares = familiaresService.findRegistros(exp.getExp());
		model.addAttribute("familiares", familiares);
		return "archivos_mp";
		
	}
	
	@GetMapping("/ver_datos_agresor/{id}")															//Datos agresor
	public String verDatosAgresor(Registro registro, Model model) {
		Expediente exp = expedienteService.encontrarPorId(registro.getId());
		Agresor agresor = agresorService.enocntrarPorExpediente(exp.getExp());
		Filiacion filiacion = fijacionService.encontrarFiliacion(2, exp.getId());
		DomicilioAgresor domAgresor = domagresorService.encontrarRegistro(exp.getExp());
		model.addAttribute("filiacion", filiacion);
		model.addAttribute("agresor", agresor);
		model.addAttribute("domAgresor", domAgresor);
		return "archivos_mp";
		
	}
	
	@PostMapping("/guardar_carpeta")																	//CREAR CARPETA DE INVESTIGACION
    public String registrarSeguimiento(CarpetasMP carpeta, Model model) {
		carpetasService.guardar(carpeta);
    	model.addAttribute("carpetaMPHecho", true);
    	model.addAttribute("titulo", "Centro de Justicia para las Mujeres");
    	return "index";
    }
	
	@PostMapping("/guardar_archivo")																	//CREAR ARCHIVOS
    public String registrarArchivos(ArchivosMP archivos, Model model) {
		Expediente expediente = expedienteService.encontrarPorId(archivos.getUsuaria());
		Agresor agresor = agresorService.enocntrarPorExpediente(expediente.getExp());
		archivos.setImputado(agresor.getId());
		archivosService.guardar(archivos);
    	model.addAttribute("carpetaMPHecho", true);
    	model.addAttribute("titulo", "Centro de Justicia para las Mujeres");
    	return "index";
    }

}
