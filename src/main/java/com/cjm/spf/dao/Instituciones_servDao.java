package com.cjm.spf.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cjm.spf.domain.instituciones_serv;

public interface Instituciones_servDao extends JpaRepository<instituciones_serv, Long>{
	
	//List<instituciones_serv> findByidPadre(Integer id);
	
	//instituciones_serv findByIdPadre(Integer id);
	
}
