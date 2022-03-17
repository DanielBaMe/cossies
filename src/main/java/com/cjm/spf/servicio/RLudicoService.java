package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.RLudico;

public interface RLudicoService {
	
	public void guardar(RLudico registro);
	
	public RLudico encontrarNinio(String nombre);
	
	public List<RLudico> encontrarHijosUsuaria(Long id);
	
	public RLudico perfilNino(Long id);
	
	public Long conteoRegistros(Integer mes, Integer anio);

}
