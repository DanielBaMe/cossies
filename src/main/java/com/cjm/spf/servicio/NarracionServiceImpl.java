package com.cjm.spf.servicio;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.cjm.spf.domain.Expediente;
import com.cjm.spf.domain.Narracion;

import com.cjm.spf.dao.ExpedienteDao;
import com.cjm.spf.dao.NarracionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NarracionServiceImpl implements NarracionService{
	
	@Autowired
    private ExpedienteDao expedienteDao;
    
    @Autowired
    private NarracionDao narracionDao;

	@Override
	@Transactional
	public void guardar(Narracion narracion) {
		Expediente expediente =expedienteDao.findTopByOrderByIdDesc();
		narracion.setExp(expediente.getExp());
		narracionDao.save(narracion);		
	}

	@Override
	@Transactional(readOnly = true)
	public Narracion encontrarNarracion(String exp) {
		return narracionDao.findByExp(exp);
	}

}
