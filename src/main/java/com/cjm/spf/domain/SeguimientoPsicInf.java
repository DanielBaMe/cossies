package com.cjm.spf.domain;

import java.io.Serializable;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "seguimiento_psic_inf")
public class SeguimientoPsicInf implements Serializable{
	
private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonFormat(pattern="yyyy")
    private Integer anio;
    
    @JsonFormat(pattern="MM")
    private Integer mes;
    
    @JsonFormat(pattern="dd")
    private Integer dia;
    
    private Long folio;
    
    private Integer no_sesion;
    
    private String h_entrada;
    
    private String h_salida;
    
    private String objetivo;
    
    private String tarea;
    
    private String psicologa;
    
    private Long nino;
    
    private String observaciones;
    
    private String sesion;
    
    private String hora;

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

	public Long getFolio() {
		return folio;
	}

	public void setFolio(Long folio) {
		this.folio = folio;
	}

	public Integer getNo_sesion() {
		return no_sesion;
	}

	public void setNo_sesion(Integer no_sesion) {
		this.no_sesion = no_sesion;
	}

	public String getH_entrada() {
		return h_entrada;
	}

	public void setH_entrada(String h_entrada) {
		this.h_entrada = h_entrada;
	}

	public String getH_salida() {
		return h_salida;
	}

	public void setH_salida(String h_salida) {
		this.h_salida = h_salida;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(String psicologa) {
		this.psicologa = psicologa;
	}

	public Long getNino() {
		return nino;
	}

	public void setNino(Long nino) {
		this.nino = nino;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
    
    

}
