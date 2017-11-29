package com.grupo4.inversiones.tools;
import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.Indicador;
import com.grupo4.inversiones.entidades.Metodologia;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.repositorio.Repositorio;

public class CargadorDeBaseDeDatos {
	
	static Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
	
	public static void guardarIndicador(Indicador indicador){
		repositorio.indicadores().persistir(indicador);
	}
	
	public static void guardarCondicionOrden(CondicionOrden condicion){
		repositorio.condicionesOrden().persistir(condicion);
	}
	
	public static void guardarCondicionFiltro(CondicionFiltro condicion){
		repositorio.condicionesFiltro().persistir(condicion);
	}
	
	public static void guardarMetodologia(Metodologia metodologia){
		repositorio.metodologias().persistir(metodologia);
	}
}