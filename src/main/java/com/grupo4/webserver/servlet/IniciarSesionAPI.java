package com.grupo4.webserver.servlet;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.inversiones.Usuario;
import com.grupo4.inversiones.servicios.Servicios;
import com.grupo4.webserver.Sesion;
import com.grupo4.webserver.utils.AuthUtils;

@RestController
@RequestMapping("/api/iniciarsesion")

public class IniciarSesionAPI {
	
	Servicios servicios = Servicios.getInstance();
	
	
	@RequestMapping(method = GET)
	public @ResponseBody Sesion iniciarSesion(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
								 @RequestParam(value = "password", defaultValue = "", required = false) String password) throws Exception{
		
		
		Usuario usuario = servicios.getIniciarSesionServicio().iniciarSesion(nombre, password);
		if (usuario != null) {
			return new Sesion(AuthUtils.generarToken(usuario));
		}
		else {
			throw new Exception("Usuario o contraseña incorrectos.");
		}
	}
	@ExceptionHandler(Exception.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value(), "Usuario o contraseña incorrectos.");
	}
	
}
