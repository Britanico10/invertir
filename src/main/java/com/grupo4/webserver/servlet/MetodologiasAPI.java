package com.grupo4.webserver.servlet;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.inversiones.entidades.Metodologia;
import com.grupo4.inversiones.servicios.Servicios;
import com.grupo4.webserver.utils.AuthUtils;

@RestController
@RequestMapping("/api/metodologias")
public class MetodologiasAPI {
	
	Servicios servicios = Servicios.getInstance();
	
	@RequestMapping(method = GET)
	public List<Metodologia> retornarMetodologias(@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getMetodologiaServicio().getMetodologias(userId);
	}
	
	@RequestMapping("/aplicar")
	public String aplicarMetodologiaA(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombreMetodologia,
			@RequestParam(value = "token", defaultValue = "", required = false) String token) {
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		try {
			return servicios.getMetodologiaServicio().aplicarMetodologia(nombreMetodologia, userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(method = DELETE)
	public List<Metodologia> eliminarMetodologia(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		return servicios.getMetodologiaServicio().eliminarMetodologia(nombre, userId);
	}
	
	@RequestMapping(method = POST)
	public List<Metodologia> agregarMetodologia(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombreMetodologia,
			@RequestParam(value = "condicionesFiltro", defaultValue = "", required = false) List<String> condicionesFiltro,
			@RequestParam(value = "condicionesOrden", defaultValue = "", required = false) List<String> condicionesOrden,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getMetodologiaServicio().agregarMetodologia(userId, nombreMetodologia, condicionesFiltro, condicionesOrden);
	}
	
	@RequestMapping(method = PUT)
	public List<Metodologia> editarNombre(@RequestParam(value = "nombre", defaultValue = "", required = false) String nombre,
			@RequestParam(value = "nuevoNombre", defaultValue = "", required = false) String nuevoNombre,
			@RequestParam(value = "token", defaultValue = "", required = false) String token){
		
		long userId = AuthUtils.validarToken(token);
		if (userId == -1L) {
			return null;
		}
		
		return servicios.getMetodologiaServicio().editarNombreMetodologia(nombre, nuevoNombre, userId);
	}
		

}
