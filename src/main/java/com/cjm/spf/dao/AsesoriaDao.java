package com.cjm.spf.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjm.spf.domain.Asesoria;

public interface AsesoriaDao extends JpaRepository<Asesoria, Long>{
	
	@Query(value = "SELECT count(*) as total FROM asesoria ases " 
			+ "			WHERE  encargada =:encargada AND mes =:mes"
			+ "			AND anio =:anio", nativeQuery = true)
	Long informeMensualByServicio(String encargada, Integer mes, Integer anio);

}
