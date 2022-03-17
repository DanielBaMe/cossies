package com.cjm.spf.servicio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.AgendaPsicDao;
import com.cjm.spf.dao.RegistroDao;
import com.cjm.spf.dao.UsuarioDao;

import com.cjm.spf.domain.AgendaPsic;


@Service
public class AgendaPsicServiceImpl implements AgendaPsicService{
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	AgendaPsicDao agendaDao;
	
	@Autowired
	RegistroDao registroDao;

	@Override
	@Transactional
	public void guardar(AgendaPsic agenda) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		System.out.println(currentPrincipalName);
		agenda.setPsicologa(currentPrincipalName);
		agendaDao.save(agenda);
		
	}

	@Override
	@Transactional(readOnly = true)
	public AgendaPsic buscar(Long id) {
		AgendaPsic agenda = agendaDao.findTopByUsuariaOrderByIdDesc(id);
		return agenda;
	}

}
