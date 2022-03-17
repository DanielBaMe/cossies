package com.cjm.spf.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "carpeta_mp")
public class CarpetasMP implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long usuaria;
    
    private String fecha;
    
    private String folio;
    
    private Integer folion;
    
    private Integer folioannio;
    
    private Integer mes;
    
    private Integer anio;


	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getFolioannio() {
		return folioannio;
	}

	public void setFolioannio(Integer folioannio) {
		this.folioannio = folioannio;
	}

	public Integer getFolion() {
		return folion;
	}

	public void setFolion(Integer folion) {
		this.folion = folion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuaria() {
		return usuaria;
	}

	public void setUsuaria(Long usuaria) {
		this.usuaria = usuaria;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}
    
    

}
