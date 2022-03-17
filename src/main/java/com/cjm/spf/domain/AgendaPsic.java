package com.cjm.spf.domain;

import java.io.Serializable;
import javax.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name = "agenda_psic")
public class AgendaPsic implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fecha;
    
    private Long usuaria;
    
    private String psicologa;
    
    private String hora;
    

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getUsuaria() {
		return usuaria;
	}

	public void setUsuaria(Long usuaria) {
		this.usuaria = usuaria;
	}

	public String getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(String psicologa) {
		this.psicologa = psicologa;
	}
    
    

}
