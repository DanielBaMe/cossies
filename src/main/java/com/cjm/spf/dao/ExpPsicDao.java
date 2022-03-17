package com.cjm.spf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjm.spf.domain.ExpPsic;

public interface ExpPsicDao extends JpaRepository<ExpPsic, Long>{
	
	ExpPsic findTopByOrderByIdDesc();
	
	ExpPsic findByUsuaria(Long id);
	
	Long countByMesAndAnio(Integer mes, Integer anio);											//USUARIAS DE PRIMERA VEZ
	
	Long countByMesAndAnioAndFisica(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA FISICA
	
	Long countByMesAndAnioAndPsic(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA PSICOLOGICA
	
	Long countByMesAndAnioAndSex(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA SEXUAL
	
	Long countByMesAndAnioAndEcono(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA ECONOMICA
	
	Long countByMesAndAnioAndPatri(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA PATRIMONIAL
	
	Long countByMesAndAnioAndCyber(Integer mes, Integer anio, String fisica);					//CONTEO DE USUARIAS DE VIOLENCIA CYBERNETICA

}
