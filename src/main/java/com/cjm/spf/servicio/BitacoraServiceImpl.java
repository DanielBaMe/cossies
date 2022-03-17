package com.cjm.spf.servicio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.dao.BitacoraDao;
import com.cjm.spf.domain.Bitacora;

@Service
public class BitacoraServiceImpl implements BitacoraService{

	@Autowired
	BitacoraDao bitacoraDao;
	
	@Override
	@Transactional
	public void guardar(Bitacora bitacora) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		bitacora.setDia(localDate.getDayOfMonth());
		bitacora.setMes(localDate.getMonthValue());
		bitacora.setAnio(localDate.getYear());
		bitacoraDao.save(bitacora);
		
	}

	@Override
	@Transactional(readOnly= true)
	public List<Bitacora> findBitacoras(Long id) {
		List<Bitacora> registros = bitacoraDao.findByRegistro(id);
		return registros;
	}

	@Override
	@Transactional(readOnly= true)
	public Bitacora findRegistro(Long id) {
		Bitacora bitacora = bitacoraDao.findById(id).orElse(null);
		return bitacora;
	}

	@Override
	@Transactional(readOnly= true)
	public Long conteoMensual(Integer mes, Integer anio) {
		Long conteo = bitacoraDao.countByMesAndAnio(mes, anio);
		return conteo;
	}

	@Override
	public Bitacora lastRegistro(Long id) {
		Bitacora ultimo = bitacoraDao.findTopByRegistroOrderByIdDesc(id);
		return ultimo;
	}

}
