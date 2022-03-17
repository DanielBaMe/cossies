package com.cjm.spf.servicio;
import java.util.List;

import com.cjm.spf.domain.ArchivosMP;

public interface ArchivosMPService {
	
	public void guardar(ArchivosMP archivo);
	
	public List<ArchivosMP> encontrarArchivos(Long id);
	
	public Long encontrarPorMesyAnio(Integer mes, Integer anio);

}
