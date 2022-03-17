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

import com.cjm.spf.domain.Expediente;
import com.cjm.spf.domain.ExpPsic;
import com.cjm.spf.domain.Registro;
import com.cjm.spf.domain.ServiciosBrindados;
import com.cjm.spf.domain.SeguimientoPsic;
import com.cjm.spf.domain.SeguimientoPsicInf;
import com.cjm.spf.pojo.ValoracionPsicPojo;
import com.cjm.spf.domain.AgendaPsic;
import com.cjm.spf.domain.AgendaPsicInf;
import com.cjm.spf.pojo.SeguimientoPsicPOJO;
import com.cjm.spf.pojo.SegInfPOJO;
import com.cjm.spf.domain.ValoracionPsicInf;

import com.cjm.spf.servicio.*;

import com.cjm.spf.dao.ExpedienteDao;
import com.cjm.spf.dao.RegistroDao;
import com.cjm.spf.dao.SegPsicDao;

@Controller
@Slf4j
public class PsicController {
	
	@Autowired
	ExpedienteDao expedienteDao;
	
	@Autowired
	RegistroDao registroDao;
	
	@Autowired
	SegPsicDao seguimientoDao;
	
	@Autowired
	private ValPsicInfService valoracionInfService;
	
	@Autowired
	private AgendaPsicService agendaService;
	
	@Autowired
    private ExpedienteService expedienteService;
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private ServiciosBrindadosService serviciosService;
	
	@Autowired
	private ExpPsicService expPsicService;
	
	@Autowired
	private SegPsicService seguimientoService;
	
	@Autowired
	private SegPsicInfService seguimientoInfService;
	
	@Autowired	
	private AgendaPsicInfService agendaInfService;
	
    
	@GetMapping("/expediente_psicologico/{id}")
	public String abrirExp(@PathVariable ("id") Long id, ValoracionPsicPojo valoracionPojo, Model model) {
		long identificador = id;
		valoracionPojo.setId_usuaria(identificador);
		model.addAttribute("valoracionPojo", valoracionPojo);
		String titulo = "Valoración psicologica";
		model.addAttribute("titulo", titulo);
		return "exp_psic";
	}
	
	
	@GetMapping("/conteo_mes_actual")
	public String conteoActual(Model model) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		long conteo = expPsicService.contar(mes, anio);
		long cFisica = expPsicService.contFisica(mes, anio, "Fisica");
		long conteoIntervalo = 0;
		int m = 11;
		int a1 = 2020;
		int m2 = 2;
		int a2= 2021;
		int i = 0;
		int y = a1;
		int doce = 12;
		for(y = a1; y <= a2; y++){
			
			for(i=m; i<=doce; i++) {
				conteoIntervalo = conteoIntervalo + expPsicService.contar(i, y);
			}
			doce = m2;
			m = 0;
		}
			
		System.out.println(conteoIntervalo);
		System.out.println(cFisica);
		long seguimiento = seguimientoService.contar(mes, anio);
		String titulo = "Informe mensual";
		model.addAttribute("seguimiento", seguimiento);
		model.addAttribute("conteo", conteo);
		model.addAttribute("titulo", titulo);
		model.addAttribute("fisica", cFisica);
		return "infMenTS";
	}
	
	@PostMapping("/expPsic")
	public String guardar(@Valid ValoracionPsicPojo valoracionPojo, Errors errores, Model model ) {
		if(errores.hasErrors()) {
			return "exp_psic";
		}
		System.out.println(valoracionPojo);
		ExpPsic expPsic = new ExpPsic();
		expPsic.setCanalizada(valoracionPojo.getCanalizada());
		expPsic.setDx(valoracionPojo.getDx());
		expPsic.setFamiliar(valoracionPojo.getFamiliar());
		expPsic.setFamiliograma(valoracionPojo.getFamiliograma());
		expPsic.setUsuaria(valoracionPojo.getId_usuaria());
		expPsic.setIndividual(valoracionPojo.getIndividual());
		expPsic.setLaboral(valoracionPojo.getLaboral());
		expPsic.setMotivo_consulta(valoracionPojo.getMotivo_consulta());
		expPsic.setObjetivo(valoracionPojo.getObjetivo());
		expPsic.setObserv(valoracionPojo.getObserv());
		expPsic.setPsicologa(valoracionPojo.getPsicologa());
		expPsic.setSexual(valoracionPojo.getSexual());
		expPsic.setSocial(valoracionPojo.getSocial());
		expPsic.setTarea(valoracionPojo.getTarea());
		
		expPsic.setSex(valoracionPojo.getSex());
		expPsic.setPsic(valoracionPojo.getPsic());
		expPsic.setPatri(valoracionPojo.getPatri());
		expPsic.setCyber(valoracionPojo.getCyber());
		expPsic.setFisica(valoracionPojo.getFisica());
		expPsic.setEcono(valoracionPojo.getEcono());
		expPsicService.guardar(expPsic);
		
		
		
		AgendaPsic agenda = new AgendaPsic();
		agenda.setHora(valoracionPojo.getHora());
		agenda.setFecha(valoracionPojo.getFecha());
		agenda.setUsuaria(valoracionPojo.getId_usuaria());
		agendaService.guardar(agenda);
		model.addAttribute("registrado", true);
		return "index";
		
	}
	
	@GetMapping("/canalizar_usuaria_ts/{id}")															//Canarlizar usuaria a empoderamiento
    public String buscarUsuaria(Registro registro, Model model) {
        registro = registroService.encontrarRegistro(registro);
        Expediente exp = expedienteService.encontrarPorId(registro.getId());
        ServiciosBrindados serv = serviciosService.encontrarExp(exp.getId());
        serv.setEmpoderamiento(1);
        serviciosService.actualizar(serv);
        return "CanalizacionTs";
    }
	
    
	@GetMapping("/seguimiento_psic/{id}") 																// Seguimiento psicologico									
	public String crearSeguimiento(@PathVariable ("id") Long id, SeguimientoPsicPOJO seguimiento, Model model) {
		seguimiento.setUsuaria(id);
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 Date date = new Date();
		 String horaActual = dateFormat.format(date);
		 seguimiento.setH_entrada(horaActual);
		String titulo = "Seguimiento Psicólogico";
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
		model.addAttribute("seguimiento", seguimiento);
		model.addAttribute("titulo", titulo);
		return "SegPsic";
	}
    
    @PostMapping("/nuevo_seguimiento")																	//Guardar seguimiento
    public String guardar_seguimiento(SeguimientoPsicPOJO seguimiento, Model model, AgendaPsic agenda) {
    	 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 Date date = new Date();
		 String horaSalida = dateFormat.format(date);
		 
    	agenda.setFecha(seguimiento.getFecha());
    	agenda.setUsuaria(seguimiento.getUsuaria());
    	agenda.setPsicologa(seguimiento.getPsicologa());
    	agendaService.guardar(agenda);
    	
    	SeguimientoPsic seguimientoPsic = new SeguimientoPsic();
    	
		seguimientoPsic.setH_entrada(seguimiento.getH_entrada());
    	seguimientoPsic.setH_salida(horaSalida);
    	seguimientoPsic.setObjetivo(seguimiento.getObjetivo());
    	seguimientoPsic.setPsicologa(seguimiento.getPsicologa());
    	seguimientoPsic.setTarea(seguimiento.getTarea());
    	seguimientoPsic.setUsuaria(seguimiento.getUsuaria());
    	seguimientoPsic.setObservaciones(seguimiento.getObservaciones());
    	seguimientoPsic.setSesion(seguimiento.getSesion());
		seguimientoPsic.setHora(seguimiento.getHora());
    	
		String titulo = "Centro de Justicia para Mujeres";
		model.addAttribute("titulo", titulo);
		seguimientoService.guardar(seguimientoPsic);
		model.addAttribute("seg", true);
        return "index";
    }
    
    @GetMapping("/ver_psic/{id}")
    public String verPerfilPsic(@PathVariable ("id") Long id, Model model){
    	Registro datos = registroDao.findById(id).orElse(null);									//Datos pre registro
		Expediente exp = expedienteService.encontrarPorId(datos.getId());						//Expediente hecho en TS
		ExpPsic expPsic = new ExpPsic();														//Expediente Psicologico

		model.addAttribute("registro", datos); 
		
		LocalDate now = LocalDate.now();
		model.addAttribute("now", now);

		if(exp != null) {
			
			model.addAttribute("expedienteTS", exp);
			expPsic = expPsicService.encontrarExpPsic(datos.getId());
			
			if(expPsic != null) {
				List<SeguimientoPsic> registros = seguimientoService.encontrarSeguimientosDeUsuaria(id);
				if(registros.isEmpty()) {
					model.addAttribute("seguimientos", null);
					
				}else {
					model.addAttribute("seguimientos", registros);
				}

				AgendaPsic agenda = agendaService.buscar(id);											//AGENDA
				if(agenda != null) {
					model.addAttribute("agendaPsic", agenda);
				}else {
					model.addAttribute("agendaPsic", null);
				}
				model.addAttribute("expediente", expPsic);
			}else {
				
				model.addAttribute("agendaPsic", null);
			}
			
			
			
			
		}else {
			model.addAttribute("seguimientos", null);
	        model.addAttribute("expediente", null);
	        model.addAttribute("expedienteTS", null);
		}
		String titulo = "Expediente psicologico";
		model.addAttribute("titulo", titulo);
		return "PerfilTs";
    }
    
    @PostMapping("/peritaje/{id}")
    public String peritaje(@PathVariable ("id") Long id, Model model) {
    	return "PerfilTs";
    }
    
    @PostMapping("/editar_cita")
    public String editarCita(AgendaPsic agenda, Model model) {
    	agendaService.guardar(agenda);
    	Registro id = new Registro();
    	id.setId(agenda.getUsuaria());
    	Registro registro = registroService.encontrarRegistro(id);
    	model.addAttribute("editCita", true);
    	model.addAttribute("citaUsuaria", registro.getNombre());
    	return "index";
    }

    								///////////////////////////////					INFANTIL					///////////////////////////////////
    @PostMapping("/buscar_ninio_psic")																//BUSCAR
    public String buscarNiño(String nombreNi, Model model) {

    	List<ValoracionPsicInf> niño = valoracionInfService.buscarNombre(nombreNi);
    	if(niño.isEmpty()) {
    		model.addAttribute("noNino", true);
    		
    	}else {
    		model.addAttribute("ninos", niño);
    	}
    	String titulo = "Búsqueda de niñ@";
        model.addAttribute("titulo", titulo);
    	return "index";
    }
    
    @GetMapping("/seguimiento_psic_inf/{id}")														//VISTA 	SEGUIMIENTO
    public String seguimientoInf(@PathVariable ("id") Long id, Model model) {
    	SegInfPOJO seguimiento = new SegInfPOJO();
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 Date date = new Date();
		 String horaActual = dateFormat.format(date);
		 seguimiento.setH_entrada(horaActual);
    	seguimiento.setNinio(id);
    	model.addAttribute("seguimiento", seguimiento);
    	
    	model.addAttribute("titulo", "Seguimiento infantil");
    	return "SegPsicInf";
    }
    
    @PostMapping("/seguimiento_ninio")
    public String seguimientoInfGuardar(SegInfPOJO seguimiento, Model model, AgendaPsicInf agenda) {	//CREAR SEGUIMIENTO
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String horaSalida = dateFormat.format(date);
		 
	   	agenda.setFecha(seguimiento.getFecha());
	   	agenda.setNino(seguimiento.getNinio());
	   	agenda.setPsicologa(seguimiento.getPsicologa());
	   	agendaInfService.guardar(agenda);
    	SeguimientoPsicInf segInf = new SeguimientoPsicInf();
    	
    	segInf.setH_entrada(seguimiento.getH_entrada());
    	segInf.setH_salida(horaSalida);
    	segInf.setNino(seguimiento.getNinio());
    	segInf.setObjetivo(seguimiento.getObjetivo());
    	segInf.setTarea(seguimiento.getTarea());
    	segInf.setPsicologa(seguimiento.getPsicologa());
    	segInf.setObservaciones(seguimiento.getObservaciones());
    	segInf.setHora(seguimiento.getHora());
    	segInf.setSesion(seguimiento.getSesion());
    	
    	seguimientoInfService.guardar(segInf);
    	String titulo = "Centro de Justicia para Mujeres";
		model.addAttribute("titulo", titulo);
		model.addAttribute("segInf", true);
    	return "index";
    }
    
    @GetMapping("/info_nino/{id}")																	//INFO
    public String nino(@PathVariable ("id") Long id, Model model) {
    	ValoracionPsicInf infante = valoracionInfService.buscarPorId(id);
    	AgendaPsicInf agenda = agendaInfService.buscar(id);
    	if(infante != null) {
    		List<SeguimientoPsicInf> seguimientoInf = seguimientoInfService.encontrarSeguimientosDeNino(id);
    		if(seguimientoInf.isEmpty()) {
    			model.addAttribute("seguimientos", null);
    		}else {
    			model.addAttribute("seguimientos", seguimientoInf);
    		}
    		
    		model.addAttribute("infante", infante);
    	}else {
    		model.addAttribute("infante", null);
    	}
    	model.addAttribute("titulo", "Información del niño");
    	model.addAttribute("agenda", agenda);
    	return "PerfilPsic";
    }
    	
	@GetMapping("/registro_infantil_psic/{id}")														//VISTA VALORACION
	public String valoracionInf(@PathVariable("id") Long id ,ValoracionPsicInf valoracion, Model model) {
		valoracion.setUsuaria(id);
		model.addAttribute("valoracion", valoracion);
		String titulo = "Valoración psicologica infantil";
		model.addAttribute("titulo", titulo);
		return "ValPsicInf";
	}
	
    @PostMapping("/valoracion_infantil")															//CREAR VALORACION
    public String valoracion_inf(@Valid ValoracionPsicInf valoracion, Errors errores, Model model) {
    	if(errores.hasErrors()) {
			return "ValPsicInf";
		}
    	ValoracionPsicInf ultimo = valoracionInfService.encontrarUltimo();
    	if(ultimo == null) {
    		valoracion.setFolio((long)1);
    	}else {
    		long elemento = ultimo.getFolio();
    		valoracion.setFolio(elemento + 1);
    	}
		valoracionInfService.guardar(valoracion);
		model.addAttribute("val", true);
    	return "redirect:/";
    }
    
    @PostMapping("/editar_cita_inf")																	//EDITAR CITA
    public String editarCita(AgendaPsicInf agenda, Model model) {
    	agendaInfService.guardar(agenda);
    	Long id = agenda.getNino();
    	ValoracionPsicInf registro = valoracionInfService.buscarPorId(id);
    	model.addAttribute("editCitaN", true);
    	model.addAttribute("citaNino", registro.getNombre());
    	return "index";
    }
    
}
