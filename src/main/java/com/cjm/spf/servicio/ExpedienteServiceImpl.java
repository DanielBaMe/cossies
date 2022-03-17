package com.cjm.spf.servicio;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.cjm.spf.domain.Expediente;
import com.cjm.spf.domain.Folio;

import com.cjm.spf.dao.ExpedienteDao;
import com.cjm.spf.dao.FolioDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpedienteServiceImpl implements ExpedienteService{
    
    @Autowired
    private ExpedienteDao expedienteDao;
    
    @Autowired
    private FolioDao folioDao;

    @Override
    @Transactional
    public void guardar(Expediente expediente) {
        Folio folio = folioDao.findTopByServicioOrderByIdDesc("expediente");
        expediente.setUsuaria(folio.getUsuaria());
        expedienteDao.save(expediente);
    }

    @Override
    @Transactional(readOnly = true)
    public Expediente encontrarExpediente(Expediente expediente) {
        return expedienteDao.findById(expediente.getId()).orElse(null);
    }

	@Override
	@Transactional(readOnly = true)
	public Expediente encontrarPorId(Long id) {
		return expedienteDao.findByUsuaria(id);
	}

	@Override
	public Expediente encontrarUltimoExpediente() {
		return expedienteDao.findTopByOrderByIdDesc();
	}
    
}
