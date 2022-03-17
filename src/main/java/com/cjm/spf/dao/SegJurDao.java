package com.cjm.spf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.SeguimientoJuridico;

public interface SegJurDao extends JpaRepository<SeguimientoJuridico, Long>{
	
	SeguimientoJuridico findTopByUsuariaOrderByIdDesc(Long id);
	
	List<SeguimientoJuridico> findByUsuaria(Long id);
	
	SeguimientoJuridico findTopByOrderByFolioDesc();
	
	Long countByMesAndAnio(Integer mes, Integer anio);

}
