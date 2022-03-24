package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.dao.Instituciones_servDao;
import com.cjm.spf.domain.instituciones_serv;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstitucionesServiceImpl implements InstitucionesService{
	
	@Autowired
	Instituciones_servDao instDao;

	@Override
	public void guardar(instituciones_serv institucion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public instituciones_serv findRegistro(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<instituciones_serv> findInstituciones() {
		List<instituciones_serv> instituciones = instDao.findAll();
		return instituciones;
	}

	@Override
	public instituciones_serv lastRegistro(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<instituciones_serv> findByidPadre(Integer id) {
		List<instituciones_serv> instituciones = instDao.findByidPadre(id);
		return instituciones;
	}

    

    
}
