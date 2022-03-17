package com.cjm.spf.servicio;

import com.cjm.spf.domain.Agresor;

public interface AgresorService {

	 public void guardar(Agresor agresor);
	    
	 public Agresor encontrarArchivo(Agresor agresor);
	 
	 public Agresor enocntrarPorExpediente(String exp);
	 
	 public void editar(Agresor agresor);
	 
	 public Agresor encontrarPorId(Long id);
	
}
