package com.cjm.spf.servicio;

import com.cjm.spf.domain.nuevo_domicilio;

public interface NuevoDomService {
	
	public void guardar(nuevo_domicilio nuevo);
	
	public nuevo_domicilio buscarPorUsuaria(Long id);

}
