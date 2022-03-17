package com.cjm.spf.servicio;

import java.util.List;


import com.cjm.spf.domain.CarpetasMP;

public interface CarpetaService {
	
	public void guardar(CarpetasMP carpeta);
	
	public CarpetasMP ultimaCarpeta(Long usuaria);
	
	public List<CarpetasMP> carpetas(Long usuaria);
	
	public Long findUsuariasPrimeraVez(Integer mes, Integer anio);

}
