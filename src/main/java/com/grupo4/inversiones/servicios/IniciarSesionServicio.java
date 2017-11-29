package com.grupo4.inversiones.servicios;

import com.grupo4.inversiones.Usuario;
import com.grupo4.inversiones.tools.GestorSesion;

public class IniciarSesionServicio {

	public Usuario iniciarSesion(String nombre, String password) {
		return GestorSesion.iniciarSesion(nombre, password);
	}
}
