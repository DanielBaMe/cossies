package com.cjm.spf.servicio;

import com.cjm.spf.domain.NivelRiesgo;

public interface NivelRiesgoService {
	
	public void guardar (NivelRiesgo riesgo);
	
	public NivelRiesgo encontrarPorExpediente(String exp);
	
	public String sumar(NivelRiesgo riesgo);

}
