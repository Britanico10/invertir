package com.grupo4.inversiones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.grupo4.inversiones.persistencia.Persistible;

@Entity
@Table(name = "USUARIO")
public class Usuario extends Persistible{
	
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String email;
	private String password;
	
	public Usuario() {
	}
	
	public Usuario(String nombre, String email, String password) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

}
