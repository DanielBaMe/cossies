package com.cjm.spf.servicio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjm.spf.domain.CarpetasMP;
import com.cjm.spf.dao.CarpertasMPDao;

@Service
public class CarpetaServiceImpl implements CarpetaService{
	
	@Autowired
	CarpertasMPDao carpetasDao;
	
	
	@Override
	public void guardar(CarpetasMP carpeta) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int anio = localDate.getYear();
		carpeta.setAnio(anio);
		carpeta.setMes(localDate.getMonthValue());
		CarpetasMP ultima = carpetasDao.findTopByFolioannioOrderByIdDesc(anio);
		if(ultima == null) {
			carpeta.setFolioannio(anio);
			carpeta.setFolion(1);
			carpeta.setFolio("CJM/1/" + anio );
			carpetasDao.save(carpeta);
		}else {
			carpeta.setFolioannio(anio);
			carpeta.setFolion(ultima.getFolion() + 1);

			carpeta.setFolio("CJM/"+ carpeta.getFolion() + "/" + anio);
			carpetasDao.save(carpeta);
		}
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public CarpetasMP ultimaCarpeta(Long usuaria) {
		CarpetasMP carpeta = carpetasDao.findTopByUsuariaOrderByIdDesc(usuaria);
		return carpeta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarpetasMP> carpetas(Long usuaria) {
		
		return (List<CarpetasMP>) carpetasDao.findByUsuaria(usuaria);
	}

	@Override
	@Transactional(readOnly = true)
	public Long findUsuariasPrimeraVez(Integer mes, Integer anio) {
		Long usuarias = carpetasDao.countByMesAndAnio(mes, anio);
		return usuarias;
	}

}
