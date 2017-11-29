package com.grupo4.inversiones.tools;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.repositorio.Repositorio;

public class InicializadorGlobal {
	
	static Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
	
	
	public static void inicializar() throws FileNotFoundException, SQLException {	
		
//		App.usuarioActual = new Usuario("benja", "benjamenriquez@gmail.com", "Norberto10");
//    	App.usuarioActual.setId((long)0);
//		
//		cargarIndicadores(); //App.indicadores = cargadorDeArchivos.cargarArchivoIndicadores("src/main/indicadores.txt");
//		cargarEmpresas(); //App.empresas = cargadorDeArchivos.cargarEmpresas("src/main/empresas.txt");
//		cargarCondicionesOrden(); //App.condicionesOrden = cargadorDeArchivos.cargarArchivoCondicionesOrden("src/main/condicionesOrden.txt");
//		cargarCondicionesFiltro(); //App.condicionesFiltro = cargadorDeArchivos.cargarArchivoCondicionesFiltro("src/main/condicionesFiltro.txt");
//		cargarMetodologias(); //App.metodologias = cargadorDeArchivos.cargarArchivoMetodologias("src/main/metodologias.txt");
//		
//		App.empresaActual = App.empresas.get(0);
//		App.periodoActual = 2007;
		
	}
//	
//	public static void cargarIndicadores() throws SQLException {
//		App.indicadores = repositorio.indicadores().buscarTodas();
//	}
//	
//	public static void cargarEmpresas() {
//		App.empresas = repositorio.empresas().buscarTodas();
//	}
//	
//	public static void cargarCondicionesOrden() {
//		App.condicionesOrden = repositorio.condicionesOrden().buscarTodas();
//	}
//	
//	public static void cargarCondicionesFiltro() {
//		App.condicionesFiltro = repositorio.condicionesFiltro().buscarTodas();
//	}
//	
//	public static void cargarMetodologias() {
//		App.metodologias = repositorio.metodologias().buscarTodas();
//	}
	
}
