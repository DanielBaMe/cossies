package com.cjm.spf.servicio;

import com.cjm.spf.domain.PerfilAgresor;
import com.cjm.spf.domain.Agresor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.PerfilAgresorDao;
import com.cjm.spf.dao.DatosAgresorDao;

@Service
public class PerfilAgresorServiceImpl implements PerfilAgresorService{
	
	@Autowired
	PerfilAgresorDao perfilDao;
	
	@Autowired
	DatosAgresorDao agresorDao;

	@Override
	@Transactional
	public void guardar(PerfilAgresor perfil) {
		
		perfilDao.save(perfil);
		
	}


	@Override
	@Transactional(readOnly = true)
	public PerfilAgresor encontrarRegistro(String expediente) {
		PerfilAgresor perfil = perfilDao.findByExpediente(expediente);
		return perfil;
	}

}
