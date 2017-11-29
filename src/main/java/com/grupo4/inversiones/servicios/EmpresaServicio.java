package com.grupo4.inversiones.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.Empresa;
import com.grupo4.inversiones.repositorio.Repositorio;
import com.grupo4.inversiones.tools.AplicarIndicadores;
import com.grupo4.inversiones.tools.CargadorDeArchivos;

public class EmpresaServicio {

	Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());

	public List<Empresa> getEmpresas() {
		return repositorio.empresas().buscarTodas();
	}
	
	public List<Empresa> cargarEmpresas() throws FileNotFoundException{
		List<Empresa> empresas = CargadorDeArchivos.cargarArchivoEmpresas(App.DIR_EMPRESAS);
		return empresas;
	}
	
	public List<Empresa> actualizarEmpresas() throws FileNotFoundException{
		List<Empresa> empresas = cargarEmpresas();
		repositorio.empresas().borrarTodas();
		repositorio.empresas().persistirLista(empresas);
		try {
			AplicarIndicadores.precalculo();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getEmpresas();
		
	}
}