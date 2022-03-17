package com.cjm.spf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.nuevo_domicilio;

public interface NevoDomicilioDao extends JpaRepository<nuevo_domicilio, Long>{
	
	nuevo_domicilio findByUsuaria(Long id);

}
