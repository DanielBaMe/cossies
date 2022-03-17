package com.cjm.spf.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

import javax.validation.constraints.Size;

@Entity
@Data
@Table(name="usuarios")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    //@Column(nullable=false)
    @NotEmpty(message="El campo no puede esta vacío.")
    private String nombre;
    
    @Column(nullable=false, unique=true)
    @NotEmpty(message="El campo no puede esta vacío.")
    private String username;
    
    @Column(nullable=false)
    @NotEmpty(message="El campo no puede esta vacío.")
    @Size(min=3)
    private String password;
    

    private Long privilegio;
    

    private String rol;
    

    private Integer id_status_usuario;
    
    private String nrol;
    
    private String fecha_alta;
    
    private String fecha_baja;
    
    public String getNrol() {
		return nrol;
	}

	public void setNrol(String nrol) {
		this.nrol = nrol;
	}

	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
       name="user_role",
       joinColumns = @JoinColumn(name="user_id"),
       inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Rol> roles;
    
    public void agregar(Rol tempRol) {
		if(roles==null) {
			roles = new LinkedList<Rol>();
		}
		roles.add(tempRol);
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPrivilegio() {
		return privilegio;
	}

	public void setPrivilegio(Long privilegio) {
		this.privilegio = privilegio;
	}



	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Integer getId_status_usuario() {
		return id_status_usuario;
	}

	public void setId_status_usuario(Integer id_status_usuario) {
		this.id_status_usuario = id_status_usuario;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
    
    
}

