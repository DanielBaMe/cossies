package com.cjm.spf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.SeguimientoPsicInf;

public interface SegPsicInfDao extends JpaRepository<SeguimientoPsicInf, Long>{
	
	SeguimientoPsicInf findTopByOrderByFolioDesc();
	
	SeguimientoPsicInf findTopByNinoOrderByIdDesc(Long id);
	
	List<SeguimientoPsicInf> findByNino(Long id);
	
	Long countByMesAndAnio(Integer mes, Integer anio);

}
