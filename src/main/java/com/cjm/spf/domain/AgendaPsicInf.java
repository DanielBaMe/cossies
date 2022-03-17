package com.cjm.spf.domain;

import java.io.Serializable;


import javax.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name = "agenda_psic_inf")
public class AgendaPsicInf implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fecha;
    
    private Long nino;
    
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

	public Long getNino() {
		return nino;
	}

	public void setNino(Long nino) {
		this.nino = nino;
	}

	public String getPsicologa() {
		return psicologa;
	}

	public void setPsicologa(String psicologa) {
		this.psicologa = psicologa;
	}
    
    

}
