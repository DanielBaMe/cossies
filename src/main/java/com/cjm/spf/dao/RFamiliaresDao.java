package com.cjm.spf.dao;

import com.cjm.spf.domain.RFamiliares;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RFamiliaresDao extends JpaRepository<RFamiliares, Long>{
    List<RFamiliares> findByExpediente(String exp);
}
