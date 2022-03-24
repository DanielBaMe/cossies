package com.cjm.spf.domain;

import java.io.Serializable;

import javax.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name = "instituciones")
public class instituciones_serv implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
   private String nombre_coordi;
   
   private String institucion;
    
   private String nivel_inst;

   private String correo;
   
   private String direccion;
   
   private String municipio;
   
   private String telefono;
   
   private String status;
   
   private String area;
   
   private String grupo;
   
   private Integer id_padre;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNombre_coordi() {
	return nombre_coordi;
}

public void setNombre_coordi(String nombre_coordi) {
	this.nombre_coordi = nombre_coordi;
}

public String getInstitucion() {
	return institucion;
}

public void setInstitucion(String institucion) {
	this.institucion = institucion;
}

public String getNivel_inst() {
	return nivel_inst;
}

public void setNivel_inst(String nivel_inst) {
	this.nivel_inst = nivel_inst;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getMunicipio() {
	return municipio;
}

public void setMunicipio(String municipio) {
	this.municipio = municipio;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getArea() {
	return area;
}

public void setArea(String area) {
	this.area = area;
}

public String getGrupo() {
	return grupo;
}

public void setGrupo(String grupo) {
	this.grupo = grupo;
}

public Integer getId_padre() {
	return id_padre;
}

public void setId_padre(Integer id_padre) {
	this.id_padre = id_padre;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
   
   

}