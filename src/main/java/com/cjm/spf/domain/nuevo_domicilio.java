package com.cjm.spf.domain;

import java.io.Serializable;


import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
@Table(name = "nuevo_domicilio")
public class nuevo_domicilio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long usuaria;
	
	
	private Integer numero_exterior;
	
	private Integer numero_interior;
	
	@NotBlank (message="El campo no puede esta vacío.")
	private String calle;
	
	@NotBlank (message="El campo no puede esta vacío.")
	private String colonia;
	
	@NotBlank (message="El campo no puede esta vacío.")
	private String municipio;
	
	@NotBlank (message="El campo no puede esta vacío.")
	private String estado;
	
	@NotNull (message="El campo no puede esta vacío.")
	private Integer codigo_postal;

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

	public Integer getNumero_exterior() {
		return numero_exterior;
	}

	public void setNumero_exterior(Integer numero_exterior) {
		this.numero_exterior = numero_exterior;
	}

	public Integer getNumero_interior() {
		return numero_interior;
	}

	public void setNumero_interior(Integer numero_interior) {
		this.numero_interior = numero_interior;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(Integer codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	

}
