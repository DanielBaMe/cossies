package com.cjm.spf.controllers;
import java.text.DateFormat;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cjm.spf.domain.SeguimientoJuridico;
import com.cjm.spf.domain.Usuario;
import com.cjm.spf.servicio.SegJudService;
import com.cjm.spf.servicio.RegistroService;
import com.cjm.spf.domain.Inter;
import com.cjm.spf.domain.Registro;
import com.cjm.spf.dao.UsuarioDao;

@Controller
@Slf4j
public class JuridicoController {
	
	@Autowired
	RegistroService registroService;
	
	@Autowired
	SegJudService seguimientoService;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@GetMapping("/perfil_juridico/{id}")
	public String verPerfilJud(Registro registro, Model model, SeguimientoJuridico seguimientoJud) {
		Registro usuaria = registroService.encontrarRegistro(registro);
		List<SeguimientoJuridico> seguimientos = seguimientoService.seguimientosUsuaria(registro.getId());
		
		if(seguimientos.isEmpty()) seguimientos = null;
		model.addAttribute("seguimientosJud", seguimientos);
		String titulo = "Perfil juridico";
		seguimientoJud.setUsuaria(registro.getId());
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		model.addAttribute("titulo", titulo);
		model.addAttribute("seguimientoJ", seguimientoJud);
		model.addAttribute("registro", usuaria);
		return "PerfilPsic";
	}
	
	@GetMapping("/inter/{id}")
	public String reunionInter(@PathVariable ("id") Long id, Model model) {			//INTERDISCIPLINARIO
		Inter inter = new Inter();
		inter.setUsuaria(id);
		List<Usuario> usuarias = usuarioDao.findAll();
		model.addAttribute("usuarias", usuarias);
		model.addAttribute("inter", inter);
		model.addAttribute("titulo", "Centro de Justicia para las Mujeres");
		return "interd";
	}
	
	@GetMapping("/informe_juridico")
	public String informeMensual(Model model) {			//INFORME MENSUAL
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		Long conteo = seguimientoService.contabilizarAsesorias(mes, anio);
		model.addAttribute("titulo", "Informe Mensual");
		model.addAttribute("conteo", conteo);
		return "infMenTS";
	}
	
	@PostMapping("/seguimiento_juridico")																	//CREAR SEGUIMIENTO JURIDICO
    public String registrarSeguimiento(SeguimientoJuridico seguimientoJud, Model model) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String horaSalida = dateFormat.format(date);
		
		seguimientoJud.setAnio(Integer.parseInt(seguimientoJud.getFecha().substring(0, 4)));
		seguimientoJud.setDia(Integer.parseInt(seguimientoJud.getFecha().substring(8, 10)));
		seguimientoJud.setMes(Integer.parseInt(seguimientoJud.getFecha().substring(5, 7)));

    	seguimientoService.guardar(seguimientoJud);
    	
    	
    	model.addAttribute("seguimientoJudHecho", true);
    	model.addAttribute("titulo", "Centro de Justicia para las Mujeres");
    	return "index";
    }

}
