package com.grupo4.inversiones.entidades.condiciones;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.Empresa;
import com.grupo4.inversiones.entidades.Indicador;
import com.grupo4.inversiones.repositorio.Repositorio;
import com.grupo4.inversiones.tools.AplicarIndicadores;

@Entity
public class FiltroMayor extends CondicionFiltro {

	private static final long serialVersionUID = 1L;

	public FiltroMayor(String _nombreCondicion, String _nombreIndicador, int _periodo, int _comparador) {
		super(_nombreCondicion, 0, 0, _nombreIndicador, _periodo, _comparador);
		tipo = "FiltroMayor";
	}
	
	public FiltroMayor() {
	}
	
	@Override
	public List<Empresa> filtrar(List<Empresa> empresas){

		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		
		Indicador indicador = repositorio.indicadores().buscarPorNombre(nombreIndicador);
		
		repositorio.cerrar();
		
		List<Empresa> filtradas = new ArrayList<Empresa>();
		
		for(int i = 0; i < empresas.size(); i++) {
			if (AplicarIndicadores.getIndicadorPrecalculado(indicador.getId(),empresas.get(i).getId(),periodo) > comparador) {
				filtradas.add(empresas.get(i));
			}
		}
		return filtradas;
	}

}
