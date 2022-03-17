package com.cjm.spf.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.domain.SeguimientoJuridico;
import com.cjm.spf.dao.SegJurDao;

@Service
public class SegJudServiceImpl implements SegJudService{
	
	@Autowired
	SegJurDao seguimientoDao;

	@Override
	@Transactional
	public void guardar(SeguimientoJuridico seguimiento) {
		SeguimientoJuridico ultimo = seguimientoDao.findTopByOrderByFolioDesc();
		if(ultimo == null) {
			seguimiento.setFolio(1);
		}else {
			seguimiento.setFolio(ultimo.getFolio() + 1);
		}
		
		SeguimientoJuridico lastUsuaria = seguimientoDao.findTopByUsuariaOrderByIdDesc(seguimiento.getUsuaria());
		

		if(lastUsuaria == null) {
			seguimiento.setSesion(1);
		}else {
			seguimiento.setSesion(lastUsuaria.getSesion() + 1);
		}
		seguimientoDao.save(seguimiento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public SeguimientoJuridico ultimoSeguimiento(Long id) {
		return seguimientoDao.findTopByUsuariaOrderByIdDesc(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<SeguimientoJuridico> seguimientosUsuaria(Long id) {
		return (List<SeguimientoJuridico>) seguimientoDao.findByUsuaria(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Long contabilizarAsesorias(Integer mes, Integer anio) {
		Long numero = seguimientoDao.countByMesAndAnio(mes, anio);
		return numero;
	}

}
