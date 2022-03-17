package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.SeguimientoPsicInf;

public interface SegPsicInfService {
	
	public void guardar(SeguimientoPsicInf seguimiento);
	
	public SeguimientoPsicInf encontrarUltimoFolio();
	
	public SeguimientoPsicInf encontrarUltimoSeguimiento(Long id);
	
	public SeguimientoPsicInf encontrarSeguimiento(Long id);
	
	public List<SeguimientoPsicInf> encontrarSeguimientosDeNino(Long id);
	
	public Long contar(Integer mes, Integer anio);

}
