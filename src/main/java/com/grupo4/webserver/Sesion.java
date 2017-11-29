package com.grupo4.webserver;

public class Sesion {
	
	private String token;
	
	public Sesion(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
