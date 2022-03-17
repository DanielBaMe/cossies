package com.cjm.spf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.AgendaPsicInf;

public interface AgendaPsicInfDao extends JpaRepository<AgendaPsicInf, Long>{
	
	AgendaPsicInf findTopByNinoOrderByIdDesc(Long id);

}
