package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.ValoracionPsicInf;

public interface ValPsicInfService {
	
	public void guardar(ValoracionPsicInf valoracion);
	
	public ValoracionPsicInf buscarPorId(Long id);
	
	public List<ValoracionPsicInf> buscarNombre(String nombre);
	
	public ValoracionPsicInf encontrarUltimo();
}
