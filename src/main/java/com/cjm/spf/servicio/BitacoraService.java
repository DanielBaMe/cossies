package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.Bitacora;

public interface BitacoraService {
	
	public void guardar(Bitacora bitacora);
	
	public Bitacora findRegistro(Long id);
	
	public List<Bitacora> findBitacoras(Long id);
	 
	public Long conteoMensual(Integer mes, Integer anio);
	
	public Bitacora lastRegistro(Long id);
}
