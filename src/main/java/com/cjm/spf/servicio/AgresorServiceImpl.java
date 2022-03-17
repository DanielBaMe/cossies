package com.cjm.spf.servicio;

import com.cjm.spf.domain.Agresor;
import com.cjm.spf.domain.Expediente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.DatosAgresorDao;
import com.cjm.spf.dao.ExpedienteDao;

@Service
public class AgresorServiceImpl implements AgresorService{

	@Autowired 
	DatosAgresorDao agresorDao;
	
	@
	Autowired
	ExpedienteDao expedienteDao;
	
	@Override
	@Transactional
	public void guardar(Agresor agresor) {
		Expediente expediente = expedienteDao.findTopByOrderByIdDesc();
		agresor.setExpediente(expediente.getExp());
		agresorDao.save(agresor);
	}

	@Override
	@Transactional(readOnly = true)
	public Agresor encontrarArchivo(Agresor agresor) {
		return agresorDao.findById(agresor.getId()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Agresor enocntrarPorExpediente(String exp) {
		return agresorDao.findByExpediente(exp);
	}

	@Override
	@Transactional
	public void editar(Agresor agresor) {
		agresorDao.save(agresor);
		
	}

	@Override
	public Agresor encontrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
