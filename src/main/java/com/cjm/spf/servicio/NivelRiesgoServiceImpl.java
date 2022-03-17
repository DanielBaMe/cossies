package com.cjm.spf.servicio;

import com.cjm.spf.domain.NivelRiesgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.NivelRiesgoDao;

@Service
public class NivelRiesgoServiceImpl implements NivelRiesgoService{
	
	@Autowired
	NivelRiesgoDao nriesgoDao;

	@Override
	@Transactional
	public void guardar(NivelRiesgo riesgo) {
		nriesgoDao.save(riesgo);
		
	}

	@Override
	@Transactional(readOnly = true)
	public NivelRiesgo encontrarPorExpediente(String exp) {
		NivelRiesgo riesgo = nriesgoDao.findByExpediente(exp);
		return riesgo;
	}

	@Override
	@Transactional(readOnly = true)
	public String sumar(NivelRiesgo riesgo) {
		String nivel = "";
		int suma = riesgo.getAmenazado() + riesgo.getComportamiento() + riesgo.getContacto()
		+ riesgo.getControla() + riesgo.getCritican() + riesgo.getEpisodio() + riesgo.getGolpeado()
		+ riesgo.getHumor() + riesgo.getInfidelidad() + riesgo.getPareja() + riesgo.getPolicia() + 
		riesgo.getSexuales() + riesgo.getTension();
		
		if(suma > 0 && suma < 11) {
			nivel = "Relación abusiva";
		}
		if(suma > 12 && suma < 22) {
			nivel = "Primer nivel de abuso";
		}
		if(suma > 23 && suma < 34) {
			nivel = "Abuso severo";
		}
		if(suma > 35 && suma < 45) {
			nivel = "!Abuso peligroso";
		}
		
		return nivel;
	}

}
