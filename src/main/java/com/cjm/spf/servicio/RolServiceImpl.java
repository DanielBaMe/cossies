package com.cjm.spf.servicio;

import com.cjm.spf.domain.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.RolDao;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	RolDao rolDao;
	
	@Override
	@Transactional(readOnly = true)
	public Rol encontrarRol(String nombre) {
		Rol rol = rolDao.findByNombre(nombre);
		return rol;
	}

}
