package com.cjm.spf.domain;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "inter")
public class Inter implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuaria;
    
    private Integer folio;
    
    private String fecha;
    
    private String horaInicio;
    
    private String horaCierre;
    
    private String lugar;
    
    private String agenerales;
    
    private String aespecificos;
    
    private String nombreArea;
    
    private String cargoArea;

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

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getAgenerales() {
		return agenerales;
	}

	public void setAgenerales(String agenerales) {
		this.agenerales = agenerales;
	}

	public String getAespecificos() {
		return aespecificos;
	}

	public void setAespecificos(String aespecificos) {
		this.aespecificos = aespecificos;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getCargoArea() {
		return cargoArea;
	}

	public void setCargoArea(String cargoArea) {
		this.cargoArea = cargoArea;
	}
    
    
}
