package com.cjm.spf.servicio;

import com.cjm.spf.domain.nuevo_domicilio;

import com.cjm.spf.dao.RegistroDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.NevoDomicilioDao;

@Service
public class NuevoDomServiceImpl implements NuevoDomService{
	
	@Autowired
	NevoDomicilioDao domicilioService;

	@Override
	@Transactional
	public void guardar(nuevo_domicilio nuevo) {
		domicilioService.save(nuevo);
		
	}

	@Override
	@Transactional(readOnly = true)
	public nuevo_domicilio buscarPorUsuaria(Long id) {
		nuevo_domicilio nuevo = domicilioService.findByUsuaria(id);
		return nuevo;
	}

}
