package com.cjm.spf.domain;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@Table(name = "usuaria_datos_generales")
public class Registro implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank (message="El campo no puede esta vacío.")
    private String nombre;
    
    @NotNull(message="El campo no puede esta vacío.")
    @Positive
    private Integer edad;
    
    @Size(min=1, max=30)
    @NotBlank (message="El campo no puede esta vacío.")
    private String calle;
    
    @NotNull(message="El campo no puede esta vacío.")
    private Integer numero_exterior;
    
    private Integer numero_interior;
    
    @Size(min=5, max=30)
    @NotBlank (message="El campo no puede estar vacío.")
    private String colonia;
    
    @NotBlank(message="El campo no puede estar vacío.")
    private String municipio;
   
    @NotBlank(message="El campo no puede estar vacío.")
    private String estado;
    
    @NotNull(message="El campo no puede estar vacío.")
    private Integer codigo_postal;
    
    
    @NotBlank (message="El campo no puede estar vacío.")
    private String id_estado_civil;
    
    @Size(min=10, max=10)
    @NotBlank (message="El campo no puede estar vacío.")
    private String telefono;
    
    @Column(name="curp",unique=true)
    @Size(min=5, max=18)
    @NotBlank (message="El campo no puede estar vacío.")
    private String curp;
    
    private Integer id_status_usuaria_datos_generales;
    
 
    private String hora_ingreso;
    
  
    private String hora_egreso;
    
    @NotBlank(message="El campo no puede estar vacío.")
    private String motivo_visita;
    
    private String fecha;
    
    @NotBlank
    private String tipo_atencion;
    
    private String mot_visita;
    
    private String localidad;
    
    private String pais;
    
    
    

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getMot_visita() {
		return mot_visita;
	}

	public void setMot_visita(String mot_visita) {
		this.mot_visita = mot_visita;
	}

	public String getTipo_atencion() {
		return tipo_atencion;
	}

	public void setTipo_atencion(String tipo_atencion) {
		this.tipo_atencion = tipo_atencion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
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

	public String getId_estado_civil() {
		return id_estado_civil;
	}

	public void setId_estado_civil(String id_estado_civil) {
		this.id_estado_civil = id_estado_civil;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public Integer getId_status_usuaria_datos_generales() {
		return id_status_usuaria_datos_generales;
	}

	public void setId_status_usuaria_datos_generales(Integer id_status_usuaria_datos_generales) {
		this.id_status_usuaria_datos_generales = id_status_usuaria_datos_generales;
	}

	public String getHora_ingreso() {
		return hora_ingreso;
	}

	public void setHora_ingreso(String hora_ingreso) {
		this.hora_ingreso = hora_ingreso;
	}

	public String getHora_egreso() {
		return hora_egreso;
	}

	public void setHora_egreso(String hora_egreso) {
		this.hora_egreso = hora_egreso;
	}

	public String getMotivo_visita() {
		return motivo_visita;
	}

	public void setMotivo_visita(String motivo_visita) {
		this.motivo_visita = motivo_visita;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
    
}
