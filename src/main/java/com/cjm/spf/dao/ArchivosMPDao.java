package com.cjm.spf.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.ArchivosMP;

public interface ArchivosMPDao extends JpaRepository<ArchivosMP, Long>{
	
	List<ArchivosMP> findByUsuaria(Long id);
	
	Long countByMesAndAnio(Integer mes, Integer anio);

}
