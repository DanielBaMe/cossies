package com.cjm.spf.controllers;

import java.io.FileNotFoundException;
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

import com.cjm.spf.domain.RLudico;
import com.cjm.spf.domain.Bitacora;
import com.cjm.spf.domain.CanalizacionLudico;
import com.cjm.spf.domain.Registro;

import com.cjm.spf.servicio.BitacoraService;
import com.cjm.spf.servicio.RLudicoService;
import com.cjm.spf.servicio.CanalizacionLudicoService;
import com.cjm.spf.servicio.RegistroService;
import com.cjm.spf.dao.BitacoraDao;
import com.cjm.spf.dao.RegistroDao;

@Controller
@Slf4j
public class LudicoController {
	
	@Autowired
	RegistroDao registroDao;
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private CanalizacionLudicoService canalizacionService;
	
	@Autowired
	private BitacoraService bitacoraService;
	
	@Autowired
	private RLudicoService ludicoService;
	
	
	@GetMapping("/expediente_ludico/{id}")														//Crear expediente ludico
	public String abrirExp(@PathVariable ("id") Long id, RLudico registro, Model model) {
		long identificador = id;																
		registro.setUsuaria(identificador);
		String titulo = "Registo lúdico";
		model.addAttribute("titulo", titulo);
		model.addAttribute("registro", registro);
		return "exp_ludico";
	}
	
	
	@GetMapping("/perfil_ludico/{id}")															//Perfil de la madre en el area
	public String perfil(@PathVariable ("id") Long id, Model model) {
		Registro registro = registroDao.findById(id).orElse(null);
		List<RLudico> registros = ludicoService.encontrarHijosUsuaria(id);

		if(registros.isEmpty()) {
			model.addAttribute("registro", registro);		
			model.addAttribute("registros", null);			
		}else {
			model.addAttribute("registro", registro);		//Datos de la usuaria
			model.addAttribute("registros", registros);		//Datos de los hijos de la usuaria
		}
		//return "perfilLudico";
		String titulo = "Perfil lúdico";
		model.addAttribute("titulo", titulo);
		return "PerfilTs";
	}
	
	
	@GetMapping("/canalizar_ludico/{id}")														//Crear canalizacion
	public String canalizarLudico(@PathVariable ("id") Long id, CanalizacionLudico registro, Model model) {
		long identificador = id;
		registro.setId_registro_ludico(identificador);										
		model.addAttribute("registro", registro);
		return "canalizar_ludico";																//Buscar vista de canalizacion
	}
	
	@GetMapping("/info_bitacora/{id}")														//Ver info de bitacora
	public String verBitacora(@PathVariable ("id") Long id, Bitacora bitacora, Model model) {
		bitacora = bitacoraService.findRegistro(id);
		RLudico registro = ludicoService.perfilNino(bitacora.getRegistro());
		model.addAttribute("ludico", registro);
		model.addAttribute("bitacora", bitacora);
		model.addAttribute("titulo", "Información de bitacora");
		return "bitacoraInfo";																
	}
	
	@GetMapping("/bitacora_ludico/{id}")														//Crear bitacora
	public String bitacoraLudico(@PathVariable ("id") Long id, Bitacora bitacora, Model model) {
		long identificador = id;
		bitacora.setRegistro(identificador);	
		String titulo = "Bitacora";
		model.addAttribute("titulo", titulo);
												
		model.addAttribute("registro", bitacora);
		return "BitacoraLudico";																
	}
	
	@GetMapping("/informe_ludico")														//Conteo mensual
	public String conteoMensual(Model model) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		
		long bitacoras = bitacoraService.conteoMensual(mes, anio);
		long registros = ludicoService.conteoRegistros(mes, anio);
		model.addAttribute("titulo", "Informe mensual");
		model.addAttribute("registrosLudico", registros);
		model.addAttribute("bitacorasLudico", bitacoras);
		return "infMenTS";																
	}
	
	@PostMapping("/buscar_ninio")														//Buscar ninio registrado anteriormente
	public String buscarLudico(String nombre, Model model) {
		RLudico registro = ludicoService.encontrarNinio(nombre);											
		model.addAttribute("ludicos", registro);
		String titulo = "Búsqueda de niño";
		model.addAttribute("titulo", titulo);
		return "index";																
	}
	
	@PostMapping("/canalizar_exp_ludico")															
	public String guardar(@Valid CanalizacionLudico canalizacion, Errors errores) {
		if(errores.hasErrors()) {
			return "exp_ludico";
		}
		canalizacionService.guardar(canalizacion);
		return "redirect:/";
	}
	
	
	@PostMapping("/registro_ludico")																
	public String guardar(@Valid RLudico registro, Errors errores, Model model, Bitacora bitacora) {
		if(errores.hasErrors()) {
			return "exp_ludico";
		}
		ludicoService.guardar(registro);
		boolean regLud = true;
		String titulo = "Centro de Justicia para Mujeres";
        model.addAttribute("titulo", titulo);
		model.addAttribute("regLud", regLud);															
		return "index";
	}
	
	@PostMapping("/bitacora_ludico")																		
	public String guardar(@Valid Bitacora bitacora, Errors errores, Model model) {
		if(errores.hasErrors()) {
			return "bitacora";
		}
		Bitacora ultimo = bitacoraService.lastRegistro(bitacora.getRegistro());
		
		if(ultimo.getSesion() == null) {
			bitacora.setSesion(1);
		}else {
			bitacora.setSesion(ultimo.getSesion() + 1);
		}
		bitacoraService.guardar(bitacora);
		String titulo = "Centro de Justicia para Mujeres";
        model.addAttribute("titulo", titulo);
		boolean bit = true;
		model.addAttribute("bitacora", bit);	
		return "index";
	}
	
	@GetMapping("/perfil_lud_nino/{id}")														//Perfil del niño
	public String perfilLudic(@PathVariable ("id") Long id, RLudico registro, Model model) {
		registro = ludicoService.perfilNino(id);
		List<Bitacora> registros = bitacoraService.findBitacoras(id);
		String titulo = "Registo lúdico";
		model.addAttribute("titulo", titulo);
		model.addAttribute("perfillus", registro);
		model.addAttribute("bitacoras", registros);
		model.addAttribute("agenda", null);
		return "PerfilPsic";
	}
	
}
