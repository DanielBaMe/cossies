package com.cjm.spf.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.CarpetasMP;


public interface CarpertasMPDao extends JpaRepository<CarpetasMP, Long>{
	
	CarpetasMP findTopByUsuariaOrderByIdDesc(Long id);
	
	CarpetasMP findTopByFolioannioOrderByIdDesc(Integer anio);
	
	List <CarpetasMP> findByUsuaria(Long id);
	
	Long countByMesAndAnio(Integer mes, Integer anio);

}
