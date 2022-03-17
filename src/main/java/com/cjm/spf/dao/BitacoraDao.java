package com.cjm.spf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.Bitacora;

public interface BitacoraDao extends JpaRepository<Bitacora, Long>{
	
	public List<Bitacora> findByRegistro(Long id);
	
	Long countByMesAndAnio(Integer mes, Integer anio);	
	
	Bitacora findTopByRegistroOrderByIdDesc(Long id);
	
	//Bitacora findByNombre(String nombre);

}
