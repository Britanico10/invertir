package com.grupo4.inversiones.tools;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.repositorio.Repositorio;

public class VerificadorUsuario {
	
	public static Boolean verificarUsuarioParaCondicionOrden(Long idCond, Long idUsuario) {
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		long duenio = repositorio.condicionesOrden().buscarDuenio(idCond);
		repositorio.cerrar();
		return idUsuario.equals(duenio);
	}
	
	public static Boolean verificarUsuarioParaCondicionFiltro(Long idCond, Long idUsuario) {
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		long duenio = repositorio.condicionesFiltro().buscarDuenio(idCond);
		repositorio.cerrar();
		return idUsuario.equals(duenio);
	}
	
	public static Boolean verificarUsuarioParaIndicador(Long idIndi, Long idUsuario) {
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		long duenio = repositorio.indicadores().buscarDuenio(idIndi);
		repositorio.cerrar();
		return idUsuario.equals(duenio);
	}
	
	public static Boolean verificarUsuarioParaMetodologias(Long idDuenioMetodologia, Long idUsuario) {
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		repositorio.cerrar();
		return idUsuario.equals(idDuenioMetodologia);
	}
	
	

}
