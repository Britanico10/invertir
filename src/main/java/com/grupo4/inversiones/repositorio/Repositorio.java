package com.grupo4.inversiones.repositorio;

import javax.persistence.EntityManager;


public class Repositorio {
	private Indicadores indicadores;
	private Balances balances;
	private Empresas empresas;
	private CondicionesOrden condicionesOrden;
	private CondicionesFiltro condicionesFiltro;
	private Metodologias metodologias;
	private Usuarios usuarios;
	protected EntityManager em; 
	  
	    public Repositorio(EntityManager em){
	    	this.em = em;
	    }
	    
	    public Indicadores indicadores() {
	    	if (indicadores == null) {
	    		indicadores = new Indicadores(em);
	    	}
	    	return indicadores;
	    }
	    
	    public Metodologias metodologias() {
	    	if (metodologias == null) {
	    		metodologias = new Metodologias(em);
	    	}
	    	return metodologias;
	    }
	    
	    public Balances balances() {
	    	if (balances == null) {
	    		balances = new Balances(em);
	    	}
	    	return balances;
	    }
	    
	    public Empresas empresas() {
	    	if (empresas == null) {
	    		empresas = new Empresas(em);
	    	}
	    	return empresas;
	    }
	    
	    public CondicionesFiltro condicionesFiltro() {
	    	if (condicionesFiltro == null) {
	    		condicionesFiltro = new CondicionesFiltro(em);
	    	}
	    	return condicionesFiltro;
	    }
	    
	    public CondicionesOrden condicionesOrden() {
	    	if (condicionesOrden == null) {
	    		condicionesOrden = new CondicionesOrden(em);
	    	}
	    	return condicionesOrden;
	    }
	    
	    public Usuarios usuarios() {
	    	if (usuarios == null) {
	    		usuarios = new Usuarios(em);
	    	}
	    	return usuarios;
	    	
	    }
	    
	    public void cerrar() {
	    	em.close();
	    }
	    
}