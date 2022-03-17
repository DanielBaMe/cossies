package com.cjm.spf.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

import com.cjm.spf.domain.Usuario;

import com.cjm.spf.dao.UsuarioDao;

import com.cjm.spf.pojo.contrasenia;

@Controller
@Slf4j
public class DirectoraController {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@GetMapping("favicon.ico")
	@ResponseBody
	public void disableFavicon() {
	 //Method is void to avoid browser 404 issue by returning nothing in the response.
	}
	
	@GetMapping("/ver_usuarias")
	public String ver_usuarias(Model model) {
		List<Usuario> usuarias = usuarioDao.findAll();
		model.addAttribute("usuarias", usuarias);
		String titulo = "Lista de usuarias";
		model.addAttribute("titulo", titulo);
		return "usuariasCJM";
	}
	
	@GetMapping("/cambiar_contraseña/{id}")
	public String cambiarPass(@PathVariable ("id") Long id, Usuario usuario, Model model) {
		usuario = usuarioDao.findByidUsuario(id);
		//model.addAttribute("usuario", usuario);
		contrasenia pass = new contrasenia();
		String nombre = usuario.getNombre();
		model.addAttribute("nombre", nombre);
		pass.setId(usuario.getIdUsuario());
		model.addAttribute("contrasenia", pass);
		String titulo = "Cambio de contraseña";
		model.addAttribute("titulo", titulo);
		return "cambiarPass";
	}
	
	@PostMapping("/cambiar_clave")
	public String changePass(contrasenia contrasenia, Errors errores, Model model) {

		String pass1= contrasenia.getPass1();
		String pass2= contrasenia.getPass2();
		if(pass1.equals(pass2) ){
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Usuario usuario = new Usuario();
			
			usuario = usuarioDao.findByidUsuario(contrasenia.getId());
			String newPassword = contrasenia.getPass1();
			usuario.setPassword(encoder.encode(newPassword));
			usuarioDao.save(usuario);
			
		}else {

			model.addAttribute("error", true);
			return "cambiarPass"; 
		}
		model.addAttribute("clave", true);
		return "index";
	}

}
