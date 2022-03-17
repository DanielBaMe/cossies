package com.cjm.spf.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "archivos_mp")
public class ArchivosMP implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String delito;
    
    private String medida;
    
    private String justicia;
    
    private String estado;
    
    private Long usuaria;
    
    private Long imputado;
    
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDelito() {
		return delito;
	}

	public void setDelito(String delito) {
		this.delito = delito;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getJusticia() {
		return justicia;
	}

	public void setJusticia(String justicia) {
		this.justicia = justicia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getUsuaria() {
		return usuaria;
	}

	public void setUsuaria(Long usuaria) {
		this.usuaria = usuaria;
	}

	public Long getImputado() {
		return imputado;
	}

	public void setImputado(Long imputado) {
		this.imputado = imputado;
	}

}
