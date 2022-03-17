package com.cjm.spf.servicio;

import com.cjm.spf.domain.RFamiliares;
import com.cjm.spf.domain.Expediente;

import com.cjm.spf.dao.RFamiliaresDao;
import com.cjm.spf.dao.ExpedienteDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RFamiliaresServiceImpl implements RFamiliaresService{
    
    @Autowired
    RFamiliaresDao familiaresDao;
    
    @Autowired
    ExpedienteDao expedienteDao;

    @Override
    @Transactional
    public void guardar(RFamiliares familiares) {
        familiaresDao.save(familiares);
    }

    @Override
    @Transactional(readOnly = true)
    public RFamiliares encontrarRegistro(RFamiliares familiares) {
        return familiaresDao.findById(familiares.getId()).orElse(null);
    }

	@Override
	@Transactional(readOnly = true)
	public List<RFamiliares> findRegistros(String exp) {
		
		return (List<RFamiliares>) familiaresDao.findByExpediente(exp);
	}
    
}
