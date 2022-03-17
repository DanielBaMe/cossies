package com.cjm.spf.servicio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjm.spf.domain.Inter;
import com.cjm.spf.dao.InterDao;

@Service
public class InterServiceImpl implements InterService{

	@Autowired
	InterDao interDao;
	
	@Override
	public void guardar(Inter inter) {
		
		Inter ultimo = interDao.findTopByUsuaria(inter.getUsuaria());
		if(ultimo == null) {
			inter.setFolio(1);
		}else {
			inter.setFolio(ultimo.getFolio() + 1);
		}
		
		
		interDao.save(inter);
		
	}

	@Override
	public Inter buscarUsuaria(Long id) {
		Inter inter = interDao.findTopByUsuaria(id);
		return inter;
	}

	@Override
	public List<Inter> buscarRegistrosUsuaria(Long id) {
		
		return (List<Inter>) interDao.findByUsuaria(id);
	}

}
