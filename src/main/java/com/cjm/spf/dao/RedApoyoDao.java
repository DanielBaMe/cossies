package com.cjm.spf.dao;

import com.cjm.spf.domain.RedApoyo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RedApoyoDao extends JpaRepository<RedApoyo, Long>{
	
    List<RedApoyo> findByExpediente(String exp);
}
