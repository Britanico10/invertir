package com.grupo4.webserver.servlet;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.servicios.Servicios;
import com.grupo4.webserver.utils.AuthUtils;

@RestController
@RequestMapping("/api/condicionesfiltro")
public class CondicionesFiltroAPI {
	
	Servicios servicios = Servicios.getInstance();

	@RequestMapping(method = GET)
	public List<CondicionFiltro> retornarCondicionesFiltro(@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionFiltroServicio().getCondicionesFiltro(userId);
	}
	
	@RequestMapping(method = DELETE)
	public List<CondicionFiltro> borrarCondicionFiltro(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionFiltroServicio().borrarCondicion(nombre, userId);
		
	}
	
	@RequestMapping(method = POST)
	public List<CondicionFiltro> crearCondicionFiltro(
			@RequestParam(value = "tipo", defaultValue = "", required = false) String tipo,
			@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "inicioIntervalo", defaultValue = "", required = false) int inicioIntervalo,
			@RequestParam(value = "finIntervalo", defaultValue = "", required = false) int finIntervalo,
			@RequestParam(value = "nombreIndicador", defaultValue = "", required = false) String nombreIndicador,
			@RequestParam(value = "periodo", defaultValue = "", required = false) int periodo,
			@RequestParam(value = "comparador", defaultValue = "", required = false) int comparador,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getCondicionFiltroServicio().crearCondicion(tipo, nombre, inicioIntervalo, finIntervalo, nombreIndicador, periodo, comparador, userId);
		
	}
}
