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

import com.cjm.spf.domain.Expediente;
import com.cjm.spf.domain.RegEmpoderamiento;
import com.cjm.spf.domain.Registro;
import com.cjm.spf.domain.SeguimientoEmp;
import com.cjm.spf.domain.ServiciosBrindados;
import com.cjm.spf.domain.ExpPsic;

import com.cjm.spf.servicio.RegistroService;
import com.cjm.spf.servicio.ServiciosBrindadosService;
import com.cjm.spf.servicio.ExpedienteService;
import com.cjm.spf.servicio.RegEmpService;
import com.cjm.spf.servicio.SegEmpoService;
import com.cjm.spf.servicio.ExpPsicService;

import com.cjm.spf.dao.RegistroDao;

@Controller
@Slf4j
public class EmpoderamientoController {
	
	@Autowired
	RegistroDao registroDao;
	
	@Autowired
	private ExpPsicService expPsicService;
	
	@Autowired
	private SegEmpoService seguimientoService;
	
	@Autowired
	private RegEmpService registroEmpService;
	
	@Autowired
    private ExpedienteService expedienteService;
	
	
	@GetMapping("/registro_emp/{id}")														//Crear expediente de empoderamiento
	public String abrirExp(@PathVariable ("id") Long id, RegEmpoderamiento registro, Model model) {
		long identificador = id;																
		registro.setUsuaria(identificador);
		String titulo = "Crear registro de empoderamiento";
		model.addAttribute("titulo", titulo);
		model.addAttribute("empoderamiento", registro);
		return "RegistroEmpo";
	}
	
	@GetMapping("/informe_empoderamiento")														//Obtener informe de empoderamiento
	public String informeMensual( Model model) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		List<RegEmpoderamiento> registros = registroEmpService.findUsuariasPrimeraVez(mes, anio);
		List<SeguimientoEmp> seguimientos = seguimientoService.findSeguimientosAlMes(mes, anio);
		model.addAttribute("registros", registros);
		model.addAttribute("seguimientos", seguimientos);
		String titulo = "Informe mensual";
		model.addAttribute("titulo", titulo);
		return "infMenTS";
	}
	
	@PostMapping("/registro_empoderamiento")													//Save registro
	public String guardar(@Valid RegEmpoderamiento empoderamiento, Errors errores, Model model) {
		if(errores.hasErrors()) {
			return "RegistroEmpo";
		}
		registroEmpService.guardar(empoderamiento);
		String titulo = "Centro de Justicia para Mujeres";
        model.addAttribute("titulo", titulo);
		boolean emp = true;
		model.addAttribute("empoderamiento", emp);
		return "index";
	}
	
	@GetMapping("/seguimiento_emp/{id}")														//Crear seguimiento de empoderamiento
	public String abrirSeg(@PathVariable ("id") Long id, SeguimientoEmp seguimiento, Model model) {
		long identificador = id;																
		seguimiento.setUsuaria(identificador);
		model.addAttribute("seguimiento", seguimiento);
		String titulo = "Seguimiento de empoderamiento";
		model.addAttribute("titulo", titulo);
		return "segEmpoderamiento";
	}
	
	@PostMapping("/seguimiento_empoderamiento")													//Guardar seguimiento		
	public String guardar(@Valid SeguimientoEmp seguimiento, Errors errores, Model model) {
		if(errores.hasErrors()) {
			return "segEmpoderamiento";
		}
		seguimientoService.guardar(seguimiento);
		String titulo = "Centro de Justicia para Mujeres";
		model.addAttribute("titulo", titulo);
		boolean segemp = true;
		model.addAttribute("segEmp", segemp);
		return "index";
	}
	
	@GetMapping("/perfil_emp/{id}")														//Perfil del area
	public String perfilEmpoderamiento(@PathVariable ("id") Long id,  Model model) {																
		Registro registro = registroDao.findById(id).orElse(null);
		Expediente expediente = expedienteService.encontrarPorId(id);
		ExpPsic exPsic = expPsicService.encontrarExpPsic(id);
		RegEmpoderamiento empoderamiento = registroEmpService.findByUsuaria(id);
		
		if(empoderamiento == null) {
			model.addAttribute("registroEmp", null);
		}else {
			model.addAttribute("registroEmp", empoderamiento);
		}
		
		if(exPsic == null) {
			model.addAttribute("expedientePsic", null);
		}else {
			model.addAttribute("expedientePsic", exPsic);
		}
		
		List <SeguimientoEmp> seguimientos = seguimientoService.findSeguimientoS(id);
		if(seguimientos.isEmpty()) {
			model.addAttribute("seguimientos", null);
		}else{
			model.addAttribute("seguimientos", seguimientos);
		}
		model.addAttribute("registro", registro);
		model.addAttribute("expediente", expediente);
		String titulo = "Perfil del area de empoderamiento";
		model.addAttribute("titulo", titulo);
		return "PerfilTs";
	}

}

