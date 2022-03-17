package com.cjm.spf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.Inter;

public interface InterDao extends JpaRepository<Inter, Long>{
	
	Inter findTopByUsuaria(Long id);
	
	List<Inter> findByUsuaria(Long id);

}
