package com.cjm.spf.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.cjm.spf.domain.RegEmpoderamiento;
import com.cjm.spf.domain.instituciones_serv;

import com.cjm.spf.servicio.InstitucionesService;

import com.cjm.spf.dao.Instituciones_servDao;

@Controller
@Slf4j
public class Instituciones {
	
	@Autowired
	Instituciones_servDao instDao;
	
	@Autowired
	private InstitucionesService instService;
	
	
	@GetMapping("/instituciones")														//Ver instituciones
	public String verInst(Model model) {
		List<instituciones_serv> instituciones = instService.findInstituciones();
		String titulo = "Instituciones registradas";
		model.addAttribute("titulo", titulo);
		model.addAttribute("instituciones", instituciones);
		return "instituciones";
	}
	
	@GetMapping("/crear_institucion")														//Ver instituciones
	public String crearInst(Model model) {
		
		String titulo = "Registrar nueva institucion";
		instituciones_serv institucion = new instituciones_serv();
		model.addAttribute("titulo", titulo);
		//model.addAttribute("instituciones", institucion);
		return "crear_institucion";
	}
	
	
	@PostMapping("/registro_institucion")													//Save institucion
	//public String guardar(@Valid RegEmpoderamiento empoderamiento, Errors errores, Model model) {
	public String guardar( Model model) {
		/*if(errores.hasErrors()) {
			return "RegistroEmpo";
		}*/
		//instServoce.guardar(empoderamiento);
		String titulo = "Cossies";
        model.addAttribute("titulo", titulo);
		boolean emp = true;
		model.addAttribute("institucion", emp);
		return "index";
	}
	
	@GetMapping("/registro_hijo/{id}")													//Save hijo_institucion
	//public String guardar(@Valid RegEmpoderamiento empoderamiento, Errors errores, Model model) {
	public String guardarHijo( Model model) {
		/*if(errores.hasErrors()) {
			return "RegistroEmpo";
		}*/
		//instServoce.guardar(empoderamiento);
		String titulo = "Cossies";
        model.addAttribute("titulo", titulo);
		boolean emp = true;
		model.addAttribute("institucion", emp);
		return "index";
	}

}
