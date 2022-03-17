package com.cjm.spf.domain;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "seguimiento_juridico")
public class SeguimientoJuridico implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer anio;
    
    private Integer mes;
    
    private Integer dia;
    
    private Long usuaria;
    
    private String hora_e;
    
    private String hora_s;
    
    private String seguimiento;
    
    private Integer folio;
    
    private Integer sesion;
    
    private String municipio;
    
    private String fecha;
    
    
    

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}

	public Integer getSesion() {
		return sesion;
	}

	public void setSesion(Integer sesion) {
		this.sesion = sesion;
	}

	public Long getUsuaria() {
		return usuaria;
	}

	public void setUsuaria(Long usuaria) {
		this.usuaria = usuaria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public String getHora_e() {
		return hora_e;
	}

	public void setHora_e(String hora_e) {
		this.hora_e = hora_e;
	}

	public String getHora_s() {
		return hora_s;
	}

	public void setHora_s(String hora_s) {
		this.hora_s = hora_s;
	}

	public String getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(String seguimiento) {
		this.seguimiento = seguimiento;
	}
    
    

}
