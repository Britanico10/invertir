package com.grupo4.webserver.servlet;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.servicios.Servicios;
import com.grupo4.webserver.utils.AuthUtils;

@RestController
@RequestMapping("/api/condicionesorden")
public class CondicionesOrdenAPI {
	
	Servicios servicios = Servicios.getInstance();
	
	@RequestMapping(method = GET)
	public List<CondicionOrden> retornarCondicionesOrden(@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionOrdenServicio().getCondicionesOrden(userId);
	}
	
	@RequestMapping(method = DELETE)
	public List<CondicionOrden> borrarCondicionOrden(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionOrdenServicio().borrarCondicion(nombre, userId);
		
	}
	
	@RequestMapping(method = POST)
	public List<CondicionOrden> crearCondicionOrden(
			@RequestParam(value = "tipo", defaultValue = "", required = false) String tipo,
			@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "inicioIntervalo", defaultValue = "", required = false) int inicioIntervalo,
			@RequestParam(value = "finIntervalo", defaultValue = "", required = false) int finIntervalo,
			@RequestParam(value = "nombreIndicador", defaultValue = "", required = false) String nombreIndicador,
			@RequestParam(value = "periodo", defaultValue = "", required = false) int periodo,
			@RequestParam(value = "importancia", defaultValue = "", required = false) int importancia,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionOrdenServicio().crearCondicion(tipo, nombre, inicioIntervalo, finIntervalo, nombreIndicador, periodo, importancia, userId);
		
	}

}