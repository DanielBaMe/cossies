package com.cjm.spf.servicio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjm.spf.dao.FactorRiesgoDao;
import com.cjm.spf.dao.DatosAgresorDao;

import com.cjm.spf.domain.FactorRiesgo;
import com.cjm.spf.domain.Agresor;

@Service
public class FactorRiesgoServiceImpl implements FactorRiesgoService{
	
	@Autowired
	FactorRiesgoDao riesgoDao;
	
	@Autowired
	DatosAgresorDao datosADao;

	@Override
	@Transactional
	public void guardar(FactorRiesgo riesgo) {
		Agresor agresor = datosADao.findTopByOrderByIdDesc();
		riesgo.setAgresor(agresor.getId());
		riesgoDao .save(riesgo);
		
	}

	@Override
	public FactorRiesgo buscar(Long agresor) {
		FactorRiesgo riesgo = riesgoDao.findByAgresor(agresor);
		return riesgo;
	}



}
