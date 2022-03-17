package com.cjm.spf.servicio;

import java.time.LocalDate;


import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.domain.SeguimientoPsicInf;

import com.cjm.spf.dao.SegPsicInfDao;

@Service
public class SegPsicInfServiceImpl implements SegPsicInfService{
	
	@Autowired
	SegPsicInfDao segDao;

	@Override
	@Transactional
	public void guardar(SeguimientoPsicInf seguimiento) {
		SeguimientoPsicInf seg1 = segDao.findTopByOrderByFolioDesc();
		if(seg1 == null) {
			seguimiento.setFolio((long)1);
		}else {
			seguimiento.setFolio(seg1.getFolio() + 1);
		}
		
		SeguimientoPsicInf seg2 = segDao.findTopByNinoOrderByIdDesc(seguimiento.getNino());
		System.out.println(seg2);
		if(seg2 == null) {
			seguimiento.setNo_sesion(1);;
		}else {
			seguimiento.setNo_sesion(seg2.getNo_sesion() + 1);
		}
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		seguimiento.setDia(localDate.getDayOfMonth());
		seguimiento.setMes(localDate.getMonthValue());
		seguimiento.setAnio(localDate.getYear());
		
		segDao.save(seguimiento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public SeguimientoPsicInf encontrarUltimoFolio() {
		SeguimientoPsicInf seguimiento = segDao.findTopByOrderByFolioDesc();
		return seguimiento;
	}

	@Override
	@Transactional(readOnly = true)
	public SeguimientoPsicInf encontrarUltimoSeguimiento(Long id) {
		SeguimientoPsicInf seguimiento = segDao.findTopByNinoOrderByIdDesc(id);
		return seguimiento;
	}

	@Override
	@Transactional(readOnly = true)
	public SeguimientoPsicInf encontrarSeguimiento(Long id) {
	
		SeguimientoPsicInf seguimiento = segDao.findById(id).orElse(null);
		return seguimiento;
	}

	@Override
	@Transactional(readOnly = true)
	public List<SeguimientoPsicInf> encontrarSeguimientosDeNino(Long id) {
		return (List<SeguimientoPsicInf>) segDao.findByNino(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long contar(Integer mes, Integer anio) {
		Long conteo = segDao.countByMesAndAnio(mes, anio);
		return conteo;
	}

}
