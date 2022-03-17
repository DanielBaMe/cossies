package com.cjm.spf.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.domain.AgendaPsicInf;

import com.cjm.spf.dao.AgendaPsicInfDao;

@Service
public class AgendaPsicInfServiceImpl implements AgendaPsicInfService{

	@Autowired
	AgendaPsicInfDao agendaInfDao;
	
	@Override
	@Transactional
	public void guardar(AgendaPsicInf agenda) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = auth.getName();
		agenda.setPsicologa(currentPrincipalName);
		agendaInfDao.save(agenda);
		
	}

	@Override
	@Transactional(readOnly = true)
	public AgendaPsicInf buscar(Long id) {
		AgendaPsicInf agenda = agendaInfDao.findTopByNinoOrderByIdDesc(id);
		return agenda;
	}

}
