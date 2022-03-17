package com.cjm.spf.servicio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.domain.ArchivosMP;
import com.cjm.spf.dao.ArchivosMPDao;

@Service
public class ArchivosMPServiceImpl implements ArchivosMPService{

	@Autowired
	ArchivosMPDao archivosDao;
	
	@Override
	public void guardar(ArchivosMP archivo) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int mes = localDate.getMonthValue();
		int anio = localDate.getYear();
		archivo.setAnio(anio);
		archivo.setMes(mes);
		archivosDao.save(archivo);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ArchivosMP> encontrarArchivos(Long id) {
		
		return (List <ArchivosMP>) archivosDao.findByUsuaria(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long encontrarPorMesyAnio(Integer mes, Integer anio) {
		Long archivos = archivosDao.countByMesAndAnio(mes, anio);
		return archivos;
	}

}
