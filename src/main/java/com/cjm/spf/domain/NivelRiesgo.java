package com.cjm.spf.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="nivel_riesgo")
public class NivelRiesgo implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String expediente;
	    
	    private String fecha;
	    
	    private Integer pareja;
	    
	    private Integer infidelidad;
	    
	    private Integer contacto;
	    
	    private Integer critican;
	    
	    private Integer controla;
	    
	    private Integer comportamiento;
	    
	    private Integer humor;
	    
	    private Integer tension;
	    
	    private Integer golpeado;
	    
	    private Integer amenazado;
	    
	    private Integer sexuales;
	    
	    private Integer episodio;
	    
	    private Integer policia;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getExpediente() {
			return expediente;
		}

		public void setExpediente(String expediente) {
			this.expediente = expediente;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public Integer getPareja() {
			return pareja;
		}

		public void setPareja(Integer pareja) {
			this.pareja = pareja;
		}

		public Integer getInfidelidad() {
			return infidelidad;
		}

		public void setInfidelidad(Integer infidelidad) {
			this.infidelidad = infidelidad;
		}

		public Integer getContacto() {
			return contacto;
		}

		public void setContacto(Integer contacto) {
			this.contacto = contacto;
		}

		public Integer getCritican() {
			return critican;
		}

		public void setCritican(Integer critican) {
			this.critican = critican;
		}

		public Integer getControla() {
			return controla;
		}

		public void setControla(Integer controla) {
			this.controla = controla;
		}

		public Integer getComportamiento() {
			return comportamiento;
		}

		public void setComportamiento(Integer comportamiento) {
			this.comportamiento = comportamiento;
		}

		public Integer getHumor() {
			return humor;
		}

		public void setHumor(Integer humor) {
			this.humor = humor;
		}

		public Integer getTension() {
			return tension;
		}

		public void setTension(Integer tension) {
			this.tension = tension;
		}

		public Integer getGolpeado() {
			return golpeado;
		}

		public void setGolpeado(Integer golpeado) {
			this.golpeado = golpeado;
		}

		public Integer getAmenazado() {
			return amenazado;
		}

		public void setAmenazado(Integer amenazado) {
			this.amenazado = amenazado;
		}

		public Integer getSexuales() {
			return sexuales;
		}

		public void setSexuales(Integer sexuales) {
			this.sexuales = sexuales;
		}

		public Integer getEpisodio() {
			return episodio;
		}

		public void setEpisodio(Integer episodio) {
			this.episodio = episodio;
		}

		public Integer getPolicia() {
			return policia;
		}

		public void setPolicia(Integer policia) {
			this.policia = policia;
		}
	    
	    

}
