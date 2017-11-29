package com.grupo4.inversiones.tools;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.Usuario;
import com.grupo4.inversiones.repositorio.Repositorio;

public class GestorSesion {

	static Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());

	public static Usuario iniciarSesion(String nombre, String password) {
		
		Usuario usuario = repositorio.usuarios().buscarPorNombre(nombre);
		
		if (usuario == null) {
			return usuario;
		}
		
		if (usuario.getPassword().equals(password)) {
			return usuario;
		}
		
		return null;
	}

}
