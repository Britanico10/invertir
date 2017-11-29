package com.grupo4.inversiones.servicios;

import java.util.List;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.entidades.condiciones.ConsistenciaCreciente;
import com.grupo4.inversiones.entidades.condiciones.ConsistenciaDecreciente;
import com.grupo4.inversiones.entidades.condiciones.FiltroMayor;
import com.grupo4.inversiones.entidades.condiciones.FiltroMenor;
import com.grupo4.inversiones.repositorio.Repositorio;
import com.grupo4.inversiones.tools.CargadorDeBaseDeDatos;
import com.grupo4.inversiones.tools.VerificadorUsuario;

public class CondicionFiltroServicio {
	
	Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());

	public List<CondicionFiltro> getCondicionesFiltro(long idUsuario) {
			return repositorio.condicionesFiltro().buscarTodas(idUsuario);
	}
	
	public List<CondicionFiltro> borrarCondicionPorId(long idCond, long idUsuario) {
		if (VerificadorUsuario.verificarUsuarioParaCondicionFiltro(idCond, idUsuario)) {
			repositorio.condicionesFiltro().borrarPorId(idCond);
			return getCondicionesFiltro(idUsuario);
		}
		return null;
	}
	
	public List<CondicionFiltro> borrarCondicion(String nombre, long idUsuario) {
		long idCond = repositorio.condicionesFiltro().buscarPorNombre(nombre).getId();
		if (VerificadorUsuario.verificarUsuarioParaCondicionFiltro(idCond, idUsuario)) {
			repositorio.condicionesFiltro().borrarPorId(idCond);
			return getCondicionesFiltro(idUsuario);
		}
		return null;
	}
	
	public List<CondicionFiltro> crearCondicion(String tipo, String nombre, int inicioIntervalo, int finIntervalo,
		String nombreIndicador, int periodo, int comparador, long idUsuario) {
		CondicionFiltro nuevaCondicion;
		
		switch(tipo) {
		case "FiltroMayor":
			nuevaCondicion = new FiltroMayor(nombre,nombreIndicador,periodo,comparador);
			break;
		case "FiltroMenor":
			nuevaCondicion = new FiltroMenor(nombre,nombreIndicador,periodo,comparador);
			break;
		case "ConsistenciaCreciente":
			nuevaCondicion = new ConsistenciaCreciente(nombre,inicioIntervalo,finIntervalo,nombreIndicador);
			break;
		case "ConsistenciaDecreciente":
			nuevaCondicion = new ConsistenciaDecreciente(nombre,inicioIntervalo,finIntervalo,nombreIndicador);
			break;
		default:
			return null;
		}
		
		CargadorDeBaseDeDatos.guardarCondicionFiltro(nuevaCondicion);
		
		return getCondicionesFiltro(idUsuario);
	}

}
