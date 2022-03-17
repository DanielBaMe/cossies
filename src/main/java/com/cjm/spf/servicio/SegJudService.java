package com.cjm.spf.servicio;

import java.util.List;

import com.cjm.spf.domain.SeguimientoJuridico;

public interface SegJudService {
	
	public void guardar(SeguimientoJuridico seguimiento);
	
	public SeguimientoJuridico ultimoSeguimiento(Long id);
	
	public List<SeguimientoJuridico> seguimientosUsuaria(Long id);
	
	public Long contabilizarAsesorias(Integer mes, Integer anio);

}
