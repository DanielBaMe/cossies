package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.Inter;

public interface InterService {

	public void guardar(Inter inter);
	
	public Inter buscarUsuaria(Long id);
	
	public List<Inter> buscarRegistrosUsuaria(Long id);
	
	
}
