package com.grupo4.inversiones.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grupo4.inversiones.entidades.Balance;
import com.grupo4.inversiones.entidades.Empresa;
import com.grupo4.inversiones.entidades.Indicador;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.precalculoindicadores.PeriodoPrec;

public class Listas {
	
	public static Indicador buscarIndicadorEn(List<Indicador> indicadores, String nombreIndicador){
		for(Indicador indi: indicadores) {
	    	if(indi.getIdIndicador().equals(nombreIndicador)) return indi;
	    }
	    return null;
	}
	
	public static Indicador buscarIndicadorPorId(List<Indicador> indicadores, long id){
		for(Indicador indi: indicadores) {
	    	if(indi.getId().equals(id)) return indi;
	    }
	    return null;
	}
	
	public static Balance buscarCuentaEn(List<Balance> balances,int periodo){
	    Optional<Balance> balance = balances.stream()
	        .filter(p -> p.getPeriodo() == periodo)
	        .findFirst();
	    return balance.isPresent() ? balance.get() : null;
	}
	
	public static CondicionFiltro buscarCondicionFiltroEn(List<CondicionFiltro> condiciones,String nombre){
	    for(CondicionFiltro cond: condiciones) {
	    	if(cond.getNombreCondicion().equals(nombre)) return cond;
	    }
	    return null;
	}
	
	public static CondicionOrden buscarCondicionOrdenEn(List<CondicionOrden> condiciones,String nombre){
		for(CondicionOrden cond: condiciones) {
	    	if(cond.getNombreCondicion().equals(nombre)) return cond;
	    }
	    return null;
	}
	
	public static void sacarDeLista(Object obj, List<Object> lista){
		int pos = lista.indexOf(obj);
		lista.remove(pos);
	}
	
	public static List copiarLista(List lista){
		List nuevaLista = new ArrayList();
		for(int i = 0; i < lista.size(); i++) {
			nuevaLista.add(lista.get(i));
		}
		return nuevaLista;
	}

	public static Empresa buscarEmpresaEn(String nombreEmpresa,
			List<com.grupo4.inversiones.entidades.Empresa> empresas) {
	    for(Empresa emp: empresas) {
	    	if(emp.getNombre().equals(nombreEmpresa)) return emp;
	    }
	    return null;
	}
	
	public static PeriodoPrec buscarPeriodo(List<PeriodoPrec> periodos, long año){
		for(PeriodoPrec p: periodos) {
	    	if(p.getId() == año) return p;
	    }
	    return null;
	}

}
