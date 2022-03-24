package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.instituciones_serv;

public interface InstitucionesService {
	
	public void guardar(instituciones_serv institucion);
	
	public instituciones_serv findRegistro(Long id);
	
	public List<instituciones_serv> findInstituciones();
	
	public instituciones_serv lastRegistro(Long id);
	
	public List<instituciones_serv> findByidPadre(Integer id);
}
