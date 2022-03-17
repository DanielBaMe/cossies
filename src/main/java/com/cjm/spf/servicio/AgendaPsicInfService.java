package com.cjm.spf.servicio;

import com.cjm.spf.domain.AgendaPsicInf;

public interface AgendaPsicInfService {
	
public void guardar(AgendaPsicInf agenda);
	
	public AgendaPsicInf buscar(Long id);

}
