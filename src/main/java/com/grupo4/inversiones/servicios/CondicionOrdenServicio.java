package com.grupo4.inversiones.servicios;

import java.util.List;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.entidades.condiciones.Maximizar;
import com.grupo4.inversiones.entidades.condiciones.Minimizar;
import com.grupo4.inversiones.entidades.condiciones.OrdenMayor;
import com.grupo4.inversiones.entidades.condiciones.OrdenMenor;
import com.grupo4.inversiones.repositorio.Repositorio;
import com.grupo4.inversiones.tools.CargadorDeBaseDeDatos;
import com.grupo4.inversiones.tools.VerificadorUsuario;

public class CondicionOrdenServicio {
	
	Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());

	public List<CondicionOrden> getCondicionesOrden(long idUsuario) {
		return repositorio.condicionesOrden().buscarTodas(idUsuario);
	}
	
	public List<CondicionOrden> borrarCondicionPorId(long idCond, long idUsuario) {
		if (VerificadorUsuario.verificarUsuarioParaCondicionOrden(idCond, idUsuario)) {
			repositorio.condicionesOrden().borrarPorId(idCond);
			return getCondicionesOrden(idUsuario);
		}
		return null;
	}
	
	public List<CondicionOrden> borrarCondicion(String nombre, long idUsuario) {
		long idCond = repositorio.condicionesOrden().buscarPorNombre(nombre).getId();
		if (VerificadorUsuario.verificarUsuarioParaCondicionOrden(idCond, idUsuario)) {
			repositorio.condicionesOrden().borrarPorId(idCond);
			return getCondicionesOrden(idUsuario);
		}
		return null;
	}
	
	public List<CondicionOrden> crearCondicion(String tipo, String nombre, int inicioIntervalo, int finIntervalo,
			String nombreIndicador, int periodo, int importancia, long idUsuario) {
			CondicionOrden nuevaCondicion;
			
			switch(tipo) {
			case "Maximizar":
				nuevaCondicion = new Maximizar(nombre,inicioIntervalo,finIntervalo,nombreIndicador,importancia);
				break;
			case "Minimizar":
				nuevaCondicion = new Minimizar(nombre,inicioIntervalo,finIntervalo,nombreIndicador,importancia);
				break;
			case "OrdenMayor":
				nuevaCondicion = new OrdenMayor(nombre,nombreIndicador,periodo,importancia);
				break;
			case "OrdenMenor":
				nuevaCondicion = new OrdenMenor(nombre,nombreIndicador,periodo,importancia);
				break;
			default:
				return null;
			}
			
			CargadorDeBaseDeDatos.guardarCondicionOrden(nuevaCondicion);
			
			return getCondicionesOrden(idUsuario);
		}
	
}
