package com.cjm.spf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.NivelRiesgo;

public interface NivelRiesgoDao extends JpaRepository<NivelRiesgo, Long >{
	
	NivelRiesgo findByExpediente(String exp);

}
