package com.cjm.spf.controllers;

import java.io.FileNotFoundException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.cjm.spf.domain.*;
import com.cjm.spf.dao.DatosAgresorDao;
import com.cjm.spf.dao.ExpedienteDao;
import com.cjm.spf.dao.FolioDao;
import com.cjm.spf.dao.DomicilioAgresorDao;
import com.cjm.spf.dao.UsuarioDao;

import com.cjm.spf.pojo.usuarioPojo;

import com.cjm.spf.servicio.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class ControladorInicio {
	
	@Autowired
	private NuevoDomService domicilioService;
	
	@Autowired
	private RolService rolService;
	
	
	@Autowired
	private FolioService folioService;
	
	@Autowired
	DomicilioAgresorDao domADao;
	
	@Autowired
	ExpedienteDao expedienteDao;
	
	@Autowired
	DatosAgresorDao agresorDao;
	
	@Autowired
	FolioDao folioDao;
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	private SegEmpoService segEmpService;
	
	@Autowired
	private SegPsicService seguimientoPsicService;
	
	@Autowired
	private ExpPsicService expPsicService;
	
	@Autowired
	private FactorRiesgoService fRiesgoService;

    @Autowired
    private RegistroService registroService;

    @Autowired
    private ExpedienteService expedienteService;
    
    @Autowired
    private NarracionService narracionService;
    
    @Autowired
    private RFamiliaresService familiaresService;
    
    @Autowired
    private RedApoyoService apoyoService;
    
    @Autowired
    private eViolenciaService violenciaService;
    
    @Autowired
    private SaludService saludService;
    
    @Autowired
    private FijacionAService fijacionService;
    
    @Autowired
    private AgresorService agresorService;
    
    @Autowired
    private DomAgresorService domService;
    
    @Autowired
    private PerfilAgresorService perfilAService;
    
    @Autowired
    private AgendaPsicService agendaService;
    
    @Autowired
    private NivelRiesgoService nriesgoService;
    
    @GetMapping("/nuevo_domicilio/{id}")
    public String newDom(Registro registro, Model model) {
    	registro = registroService.encontrarRegistro(registro);
    	nuevo_domicilio nuevo = new nuevo_domicilio();
    	
    	nuevo.setUsuaria(registro.getId());
    	nuevo.setCalle(registro.getCalle());
    	nuevo.setCodigo_postal(registro.getCodigo_postal());
    	nuevo.setColonia(registro.getColonia());
    	nuevo.setEstado(registro.getEstado());
    	nuevo.setMunicipio(registro.getMunicipio());
    	nuevo.setNumero_interior(registro.getNumero_interior());
    	nuevo.setNumero_exterior(registro.getNumero_exterior());
    	
    	String titulo = "Nuevo domicilio";
    	model.addAttribute("titulo", titulo);
    	model.addAttribute("nuevo", nuevo);
    	
    	
    	return "nuevo_domicilio";
    }
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        String titulo = "Centro de Justicia para las Mujeres";
        model.addAttribute("titulo", titulo);
        return "index";
    }

    @GetMapping("/crear")
    public String crear(NuevoUsuario usuario) {
        return "crear";
    }

    @GetMapping("/registrar")
    public String registro(Registro registro, Model model) {
        String titulo = "Pre registro";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String horaEntrada = dateFormat.format(date);
		registro.setHora_ingreso(horaEntrada);
        model.addAttribute("titulo", titulo);
        return "preRegistro";
    }
    

    @GetMapping("/verRegistros")
    public String visualizar(Model model) {
        List<Registro> registros = registroService.listarRegistros();
        model.addAttribute("registros", registros);
        System.out.println(registros);
        return "verUsuarias";
    }
    
    @GetMapping("/perfil_usuaria/{id}")
    public String perfilusuaria(Registro registro, Model model) {
    	registro = registroService.encontrarRegistro(registro);						//Registro de primera vez de la usuaria
    	nuevo_domicilio nuevoDom = domicilioService.buscarPorUsuaria(registro.getId());
    
    	if(nuevoDom != null){
    		model.addAttribute("nuevoDom", nuevoDom);
    	}else {
    		model.addAttribute("nuevoDom", null);
    	}
    	
    	
    	AgendaPsic agenda = agendaService.buscar(registro.getId());
    	if(agenda != null) {
    		model.addAttribute("agendaPsic", agenda);
    	}else {
    		model.addAttribute("agendaPsic", null);
    	}
    	
    	Expediente exp = expedienteService.encontrarPorId(registro.getId());		//Se busca el exp de la usuaria
    	model.addAttribute("registro", registro);									//Enviamos los datos grales de la usuaria

    	
    	if(exp != null) {															//Si existe un expediente..
            model.addAttribute("expedienteTS", exp);								//Envio del expediente
            model.addAttribute("fechaexp", exp.getFecha());
            

			SeguimientoEmp segEmp = new SeguimientoEmp();
			segEmp.setId(registro.getId());
			List <SeguimientoEmp> seguimientoEmp = segEmpService.findSeguimientoS(segEmp.getId());
			
			if(seguimientoEmp.isEmpty()) { 											//Si no hay seguimientos en empoderamiento
				model.addAttribute("psicseguimientos", null);
		
			}else {																	//Cuando si hay seguimientos
				int segemp = seguimientoEmp.size();
				model.addAttribute("psicseguimientos", segemp);

			}
			
			model.addAttribute("expedienteTS", exp);
            ExpPsic expPsic = expPsicService.encontrarExpPsic(registro.getId());
            if(expPsic == null){
                model.addAttribute("pseguimientos", null);

            }else{
                List<SeguimientoPsic> registros = seguimientoPsicService.encontrarSeguimientosDeUsuaria(registro.getId());
			
                if(registros.isEmpty()) {  												//Si no hay seguimientos psicologicos
                    model.addAttribute("pseguimientos", null);
                    String fecha1 = Integer.toString(expPsic.getDia()) + "/" + Integer.toString(expPsic.getMes()) + "/" + Integer.toString(expPsic.getAnio());
                    model.addAttribute("pexpediente", fecha1);
                }else {																	//Cuando si los hay
                    int segpsic = registros.size();
                    model.addAttribute("pseguimientos", segpsic);
                    String fecha2 = Integer.toString(expPsic.getDia()) + "/" + Integer.toString(expPsic.getMes()) + "/" + Integer.toString(expPsic.getAnio());
                    model.addAttribute("pexpediente", fecha2);
                }
            }
			

			
		}else {  																	//Si no existe un expediente
			model.addAttribute("pseguimientos", null);
	        model.addAttribute("pexpediente", null);
	        model.addAttribute("expedienteTS", null);
	        model.addAttribute("psicseguimientos", null);
			model.addAttribute("registroemp", null);
		}
    	String titulo = "Datos de la usuaria";
    	model.addAttribute("titulo", titulo);
        return "PerfilTs";
    }

    @GetMapping("/abrirExpediente/{id}")
    public String expediente(Registro registro, Model model) {
        registro = registroService.encontrarRegistro(registro);
        model.addAttribute("registros", registro);
        return "index_prueba";
    }
    
    @GetMapping("/editar_informacion/{id}")									//////////////////VISTA PARA EL MENU DE EDICION
    public String editarInfo(@PathVariable ("id") Long id,Model model) {
        String titulo = "Editar información de la usuaria";
    	model.addAttribute("titulo", titulo);
        model.addAttribute("usuaria", id);
        return "editUser";
    }
    
    @GetMapping("/editar_info_usuaria/{id}")									//////////////////EDITAR TELEFONO DE LA USUARIA
    public String editarTelUser(Registro registro,Model model) {
    	Registro tel = new Registro();
        tel = registroService.encontrarRegistro(registro);
    	String titulo = "Editar información de la usuaria";
    	model.addAttribute("titulo", titulo);
        model.addAttribute("registro", tel);
        return "editTelUsuaria";
    }
    
    @GetMapping("/editar_agresor_datos/{id}")									/////////////////EDITAR DATOS DEL AGRESOR
    public String editarAgresorData(@PathVariable("id") long id,Agresor agresor,Model model) {
    	Expediente exp = expedienteService.encontrarPorId(id);

    	Agresor data = agresorService.enocntrarPorExpediente(exp.getExp());
    	String titulo = "Editar datos del agresor";
    	model.addAttribute("titulo", titulo);
        model.addAttribute("agresor", data);
        return "editTelUsuaria";
    }
    
    @GetMapping("/editar_agresor_dom/{id}")									/////////////////EDITAR DOMICILIO DEL AGRESOR
    public String editarAgresorDom(@PathVariable("id") long id,Model model) {
    	Expediente exp = expedienteService.encontrarPorId(id);
    	DomicilioAgresor dom = domService.encontrarRegistro(exp.getExp());
    	String titulo = "Editar domicilio del agresor";
    	model.addAttribute("titulo", titulo);
        model.addAttribute("datos", dom);
        return "editAgresorDom";
    }
    
    @GetMapping("/conteoMensual")
    public String conteo(Model model) {
    	Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		Long expedientes = folioService.hacerConteo("expediente",anio, mes);
		Long info = folioService.hacerConteo("informacion", anio, mes);
		Long asesoria = folioService.hacerConteo("asesoria", anio, mes);
		String titulo = "Informe mensual";
		model.addAttribute("titulo", titulo);
        model.addAttribute("expedientes", expedientes);
        model.addAttribute("info", info);
        model.addAttribute("asesoria", asesoria);
        return "infMenTS";
    }
    
    @GetMapping("/registro_expediente/{id}")
    public String abrir_expediente(@PathVariable("id") Long id, Model model) {
        Folio folio = new Folio();
        Folio num = folioDao.findTopByServicioOrderByIdDesc("expediente");
        Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		folio.setDia(localDate.getDayOfMonth());
		folio.setMes(localDate.getMonthValue());
		folio.setAnio(localDate.getYear());
        if(num == null){
            folio.setUsuaria(id);
            folio.setId_status_folio(1);
            folio.setId_tipo_folio((long)1);
            folio.setServicio("expediente");
            folio.setAtencion("cjm");
        }else{
            long variable = num.getId_tipo_folio() + 1;
            folio.setUsuaria(id);
            folio.setId_status_folio(1);
            folio.setId_tipo_folio(variable);
            folio.setServicio("expediente");
            folio.setAtencion("cjm");
        }
        folioDao.save(folio);
        Expediente expediente = new Expediente();
        String titulo = "Apertura de expediente";
    	model.addAttribute("titulo", titulo);
        model.addAttribute("expediente", expediente);
        return "expediente";
    }
    
    @GetMapping("/red")
    public String redApoyo(RedApoyo red, Model model) {
    	String titulo = "Red de apoyo";
    	model.addAttribute("titulo", titulo);
    	model.addAttribute("apoyo", red);
    	return "red_apoyo";
    }
    
    @GetMapping("/narracion_hechos")
    public String narracion(Narracion narracion, Model model) {
    	String titulo = "Narración de los hechos";
    	model.addAttribute("titulo", titulo);
    	model.addAttribute("narracion", narracion);
    	LocalDate now = LocalDate.now();
		model.addAttribute("now", now);
    	return "narracionHechos";
    }
    
    @GetMapping("/nuevo_usuario")
    public String newUser(Model model) {
    	usuarioPojo usuario = new usuarioPojo();
    	String titulo = "Creación de usuarias del sistema";
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", titulo);
        return "nuevoUsuarioSistema";
    }
    
    //////////////////////////////////////////////////////		POST		///////////////////////////////////////////////

    @PostMapping("/guardar")																//Guardar registro de nueva usuaria
    public String guardar(@Valid Registro registro, Errors errores, Expediente expediente, Model model) {
        if (errores.hasErrors()) {
        	model.addAttribute("titulo", "Pre registro");
        	return "preRegistro";
            
            
        } else {
        	DateFormat dateFormat = new SimpleDateFormat("HH:mm");
   		 	Date date = new Date();
   		 	String horaSalida = dateFormat.format(date);
   		 	registro.setHora_egreso(horaSalida);
            String servicio = registro.getMotivo_visita();
            
            if ("expediente".equals(servicio)) {
            	registroService.guardar(registro);
                String titulo = "Expediente";
                Date hora = new Date();
       		 	String horaEntrada = dateFormat.format(hora);
                expediente.setH_entrada(horaEntrada);
                model.addAttribute("expediente", expediente);
                model.addAttribute("titulo", titulo);
                return "expediente";
            } else {
            	registroService.guardar(registro);
                model.addAttribute("registrado", true);
                model.addAttribute("titulo", "Centro de justicia para las mujeres");
                return "index";
            }
        }
    }
    
    @PostMapping("/edicion")															//	EDITAR INFORMACION DE LA USUARIA
    public String edicion(@Valid Registro registro, Errors errores) {
    	if (errores.hasErrors()) {
    		
            return "editTelUsuaria";
        } else {
        	registroService.guardar(registro);
        	return "redirect:/";
        }
    }
    
    @PostMapping("/nuevo_dom_usuaria")															//	EDITAR INFORMACION DE LA USUARIA
    public String edicion(@Valid nuevo_domicilio nuevo, Errors errores) {
    	if (errores.hasErrors()) {
    		
            return "nuevo_domicilio";
        } else {
        	domicilioService.guardar(nuevo);
        	return "redirect:/";
        }
    }
    
    @PostMapping("/edicion_agresor")															//	EDITAR INFORMACION DEL AGRESOR
    public String edicion(@Valid Agresor agresor, Errors errores) {
    	if (errores.hasErrors()) {
    		
            return "editTelUsuaria";
        } else {
        	agresorService.editar(agresor);
        	return "redirect:/";
        }
    }
    
    @PostMapping("/new_user")															//Guardar registro de nueva usuaria
    public String guardar(@Valid usuarioPojo usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "nuevoUsuarioSistema";
        } else {
        	Date date = new Date();
        	String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
        	
        	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        	String clave = usuario.getPassword();
        
        	
        	Usuario nuvo = new Usuario();
        	
        	nuvo.setId_status_usuario(1);
        	nuvo.setNombre(usuario.getNombre());
        	nuvo.setPassword(encoder.encode(clave));
        	nuvo.setUsername(usuario.getUsername());
        	
        	String selec = usuario.getRol();
        	
        	Rol rol = rolService.encontrarRol(selec);
        	nuvo.setPrivilegio(rol.getIdRol());
        	
        	switch(nuvo.getPrivilegio().toString()) {
        	case "1":
        		nuvo.setNrol("Administrador");
        		break;
        	case "2":
        		nuvo.setNrol("Trabajadora Psicología");
        		break;
	        case "3":
	    		nuvo.setNrol("Trabajadora Juridico");
	    		break;
	    	case "4":
	    		nuvo.setNrol("Trabajadora Empoderamiento");
	    		break;
	    	case "5":
        		nuvo.setNrol("Trabajadora Trabajo Social");
        		break;
        	case "6":
        		nuvo.setNrol("Trabajadora Lúdico");
        		break;
	        case "7":
	    		nuvo.setNrol("Trabajadora MP");
	    		break;
	    	case "8":
	    		nuvo.setNrol("Jefa Psicología");
	    		break;
	    	case "9":
	    		nuvo.setNrol("Jefa Juridico");
	    		break;
	    	case "10":
	    		nuvo.setNrol("Jefa Empoderamiento");
	    		break;
	    	case "11":
	    		nuvo.setNrol("Jefa Trabajo Social");
	    		break;
	    	case "12":
	    		nuvo.setNrol("Jefa Lúdico");
	    		break;
	    	case "13":
	    		nuvo.setNrol("Jefa MP");
	    		break;
	    	default:
	    		nuvo.setNrol("Desconocido");
	    		break;
	    	}
        	nuvo.setRol(usuario.getRol());
        	nuvo.setFecha_alta(fecha);
        	nuvo.agregar(rol);
        	usuarioDao.save(nuvo);
            
            return "redirect:/";
            }
    }
    

    @PostMapping("/buscar")																	//Buscar usuaria existente por nombre
    public String buscarPorNombre(String nombre, Model model) throws FileNotFoundException{
        List<Registro> datos = registroService.encontrarUsuaria(nombre);
        if(datos.isEmpty()) {
        	model.addAttribute("registros", null);
        }else {
        	 model.addAttribute("registros", datos);
        }
        String titulo = "Búsqueda de usuaria";
        model.addAttribute("titulo", titulo);
        return "index";
    }

    @PostMapping("/guardarUsuario")															//guardar nuevo admin
    public String guardar(@Valid NuevoUsuario usuario, Errors errores) {
        if (errores.hasErrors()) {
            return "crear";
        }
        return "redirect:/";
    }

    @PostMapping("/crearExpediente")														//Crear nuevo expediente
    public String guardar(@Valid Expediente expediente, Errors errores, Model model, RFamiliares familiares) {
        if (errores.hasErrors()) {
        	model.addAttribute("titulo", "Expediente");
            return "expediente";
        }else {
        	
        	String pattern = "yyyy-MM-dd";
        	Date date = new Date();
        	//String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
        	
        	
        	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        	String fecha = simpleDateFormat.format(new Date());
        	
            expediente.setFecha(fecha);
    		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
   		 	Date hora = new Date();
   		 	String horaSalida = dateFormat.format(hora);
   		 	expediente.setH_salida(horaSalida);
        	Expediente last = expedienteService.encontrarUltimoExpediente();
        	String exp = last.getId() + 1  + "/" + localDate.getYear();
        	expediente.setExp(exp);
        	expedienteService.guardar(expediente);
        	familiares.setExpediente(expediente.getExp());
            model.addAttribute("familiares", familiares);
            String titulo = "Relaciones familiares";
            model.addAttribute("titulo", titulo);
            return "familiares";
        }
    }
    
    @PostMapping("/crear_relaciones_familiares")											//Crear relaciones familiares
    public String guardar(@Valid RFamiliares familiares, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "familiares";
        }
        
        boolean band = true;
        
        RFamiliares fam = new RFamiliares();
        fam.setExpediente(familiares.getExpediente());
        familiaresService.guardar(familiares);
        
    	List<RFamiliares> lista = familiaresService.findRegistros(familiares.getExpediente());
    	
    	model.addAttribute("registros", lista);
    	model.addAttribute("band", band);
    	String titulo = "Relaciones familiares";
    	model.addAttribute("titulo", titulo);
    	model.addAttribute("familiares", fam);
        return "familiares";
    }
    
    @PostMapping("/crear_red_apoyo")														//Crear red de apoyo
    public String guardar(@Valid RedApoyo apoyo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "red_apoyo";
        }
        apoyoService.guardar(apoyo);
        
        boolean band = true;
        RedApoyo ap = new RedApoyo();
        String exp = apoyo.getExpediente();
        List<RedApoyo> red = apoyoService.findRegistros(exp);
        ap.setExpediente(apoyo.getExpediente());
        model.addAttribute("registros", red);
    	model.addAttribute("band", band);
    	String titulo = "Red de apoyo";
    	model.addAttribute("titulo", titulo);
    	model.addAttribute("apoyo", ap);
        //return "narracionHechos";
    	return "red_apoyo";
    }
    
    
    @PostMapping("/crearNarracion")															//Crear nueva narracion
    public String guardar(@Valid Narracion narracion, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "narracionHechos";
        }
        narracionService.guardar(narracion);
        EfectosViolencia violencia = new EfectosViolencia();
        violencia.setExpediente(narracion.getExp());
        model.addAttribute("violencia", violencia);
        String titulo = "Efectos de la violencia";
        model.addAttribute("titulo", titulo);
        return "efectos_violencia";
    }
    
    @PostMapping("/crearEfectos")															//Crear efectos de violencia
    public String guardar(@Valid EfectosViolencia violencia, Errors errores, SaludFisica salud, Model model) {
        if (errores.hasErrors()) {
            return "efectos_violencia";
        }
        model.addAttribute("salud", salud);
        violenciaService.guardar(violencia);
        
        String titulo = "Estado de salud";
        model.addAttribute("titulo", titulo);
        return "Salud";
    }
    
    @PostMapping("/nueva_salud")																	//Crear nuevo estado de salud
    public String guardar(@Valid SaludFisica salud, Errors errores, Filiacion filiacion, Model model ) {
        if (errores.hasErrors()) {
            return "Salud";
        }
        saludService.guardar(salud);
        model.addAttribute("filiacion", filiacion);
        String titulo = "Filiación de la usuaria";
        model.addAttribute("titulo", titulo);
        return "Filiacion";
    }
    
    @PostMapping("/crear_filiacion")														//Crear nueva filiacion
    public String guardar(@Valid Filiacion filiacion, Errors errores, Agresor agresor, Model model, Expediente expediente) {
        if (errores.hasErrors()) {
            return "Filiacion";
        }
        expediente = expedienteDao.findTopByOrderByIdDesc();
        filiacion.setCondicion(1);
        model.addAttribute("agresor", agresor);
        fijacionService.guardar(filiacion);
        String titulo = "Datos del agresor";
        model.addAttribute("titulo", titulo);
        return "DatosAgresor";
    }

    @PostMapping("/datos_agresor")															//Crear nuevo expediente de agresor
    public String guardar(@Valid Agresor agresor, Errors errores, DomicilioAgresor domAgresor, Model model ) {
        if (errores.hasErrors()) {
        	model.addAttribute("titulo", "Datos del agresor");
            return "DatosAgresor";
        }
       
        agresorService.guardar(agresor);
        Agresor ultimo = agresorService.encontrarArchivo(agresor);
        domAgresor.setExpediente(ultimo.getExpediente());
        System.out.println(domAgresor.getExpediente());
        model.addAttribute("domAgresor", domAgresor);
        String titulo = "Domicilio del agresor";
        model.addAttribute("titulo", titulo);
        return "DomicilioAgresor";
    }
    
    @PostMapping("/domicilio_agresor")														//Crear nuevo dom agresor
    public String guardar(@Valid DomicilioAgresor domAgresor, Errors errores, PerfilAgresor pAgresor, Model model ) {
        if (errores.hasErrors()) {
            return "DomicilioAgresor";
        }
        
        domService.guardar(domAgresor);
        pAgresor.setExpediente(domAgresor.getExpediente());
        model.addAttribute("pAgresor", pAgresor);
        String titulo = "Perfil del agresor";
        model.addAttribute("titulo", titulo);
        return "PerfilAgresor";
    }
    
    @PostMapping("/perfil_agresor")															//Crear perfil agresor
    public String guardar(@Valid PerfilAgresor pAgresor, Errors errores, Filiacion filiacion, Model model) {
        if (errores.hasErrors()) {
            return "PerfilAgresor";
        }
        perfilAService.guardar(pAgresor);
        String titulo = "Filiacion del agresor";
        model.addAttribute("titulo", titulo);
        return "FiliacionAgresor";
    }
    
    
    @PostMapping("/filiacion_agresor")														//Crear filiacion del agresor
    public String guardar(@Valid Filiacion filiacion, Errors errores, Agresor agresor, Model model, FactorRiesgo fRiesgo) {
        if (errores.hasErrors()) {
            return "FiliacionAgresor";
        }
        agresor = agresorDao.findTopByOrderByIdDesc();
        filiacion.setCondicion(2);
        fijacionService.guardar(filiacion);
        model.addAttribute("fRiesgo", fRiesgo);
        String titulo = "Factor de riesgo";
        model.addAttribute("titulo", titulo);
        return "factor_riesgo";
    }
    
    @PostMapping("/factor_riesgo")															//Crear factores de riesgo
    public String guardar(@Valid FactorRiesgo fRiesgo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "factor_riesgo";
        }
        fRiesgoService.guardar(fRiesgo);
        NivelRiesgo riesgo = new NivelRiesgo();
        Agresor agresor = new Agresor();
        agresor.setId(fRiesgo.getAgresor());
        Agresor expAgresor = agresorService.encontrarArchivo(agresor);
        
        String pattern = "MM-dd-yyyy";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(new Date());
    	riesgo.setFecha(date);
        riesgo.setExpediente(expAgresor.getExpediente());
        model.addAttribute("riesgo", riesgo);
        String titulo = "Centro de Justicia para Mujeres";
        model.addAttribute("titulo", titulo);
       
        return "nivel_riesgo";
    }
    
    @PostMapping("/nivel_riesgo")															//Crear factores de riesgo
    public String guardarNivelRiesgo(@Valid NivelRiesgo riesgo, Errors errores, Model model) {
        if (errores.hasErrors()) {
            return "nivel_riesgo";
        }
        nriesgoService.guardar(riesgo);
        String nivel = nriesgoService.sumar(riesgo);
        
        String titulo = "Centro de Justicia para Mujeres";
        model.addAttribute("suma", nivel);
        model.addAttribute("titulo", titulo);
        boolean listo = true;
        model.addAttribute("listo", listo);
        return "index";
    }
    
    @GetMapping("/info_detallada/{id}")																									//VER TODA LA INFO DE LA USUARIA
    public String masInformacion(Registro registro, Model model) {
    	Registro info_usuaria = registroService.encontrarRegistro(registro);
    	Expediente expediente = expedienteService.encontrarPorId(registro.getId());
    	List<RFamiliares> familiares = familiaresService.findRegistros(expediente.getExp());
    	List<RedApoyo> red = apoyoService.findRegistros(expediente.getExp());
    	Narracion narracion = narracionService.encontrarNarracion(expediente.getExp());
    	EfectosViolencia efecto = violenciaService.encontrar(expediente.getExp());
    	SaludFisica salud = saludService.encontrar(expediente.getExp());
    	Filiacion filiacion = fijacionService.encontrarFiliacion(1, expediente.getId());
    	String titulo = "Información detallada de la usuaria";
      
        model.addAttribute("titulo", titulo);
        model.addAttribute("usuaria", info_usuaria);
        model.addAttribute("expediente", expediente);
        model.addAttribute("familiares", familiares);
        model.addAttribute("red", red);
        model.addAttribute("narracion", narracion);
        model.addAttribute("salud", salud);
        model.addAttribute("efecto", efecto);
        model.addAttribute("filiacion", filiacion);

        return "info_detallada";
    }
    
    @GetMapping("/info_detallada_agresor/{id}")																									//VER TODA LA INFO DEL AGRESOR
    public String infoAgresor(Registro registro, Model model) {
    	Registro info_usuaria = registroService.encontrarRegistro(registro);
    	Expediente expediente = expedienteService.encontrarPorId(registro.getId());
    	Agresor agresor = agresorService.enocntrarPorExpediente(expediente.getExp());
    	DomicilioAgresor domicilio = domService.encontrarRegistro(expediente.getExp());
    	PerfilAgresor perfil = perfilAService.encontrarRegistro(expediente.getExp());
    	Filiacion filiacion = fijacionService.encontrarFiliacion(2, expediente.getId());
    	FactorRiesgo factor = fRiesgoService.buscar(agresor.getId());
    	
        model.addAttribute("titulo", "Información del agresor");
       /* model.addAttribute("usuaria", info_usuaria);
        model.addAttribute("expediente", expediente);*/
        System.out.println(domicilio.getPais());
        model.addAttribute("agresor", agresor);
        model.addAttribute("domicilio", domicilio);
        model.addAttribute("perfil", perfil);
        model.addAttribute("factor", factor);
        model.addAttribute("filiacion", filiacion);

        return "info_detallada_agresor";
    }
    
}
