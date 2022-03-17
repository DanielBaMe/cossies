package com.cjm.spf.servicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.RegistroLudicoDao;

import com.cjm.spf.domain.RLudico;

@Service
public class RLudicoServiceImpl implements RLudicoService{
	
	@Autowired
	RegistroLudicoDao ludicoDao;

	@Override
	@Transactional
	public void guardar(RLudico registro) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		registro.setDia(localDate.getDayOfMonth());
		registro.setMes(localDate.getMonthValue());
		registro.setAnio(localDate.getYear());
		RLudico primero = ludicoDao.findTopByOrderByIdDesc();
		if(primero == null) {
			registro.setFolio((long)1);
		}else {
			registro.setFolio(primero.getFolio() + 1);
		}
		ludicoDao.save(registro);
		
	}

	@Override
	@Transactional(readOnly = true)
	public RLudico encontrarNinio(String nombre) {
		RLudico registro = ludicoDao.findByNombreContaining(nombre);
		return registro;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RLudico> encontrarHijosUsuaria(Long id) {
		return (List<RLudico>) ludicoDao.findByUsuaria(id);
	}

	@Override
	@Transactional(readOnly = true)
	public RLudico perfilNino(Long id) {
		RLudico registro = ludicoDao.findById(id).orElse(null);
		return registro;
	}

	@Override
	@Transactional(readOnly = true)
	public Long conteoRegistros(Integer mes, Integer anio) {
		Long conteo  = ludicoDao.countByMesAndAnio(mes, anio);
		return conteo;
	}

}
