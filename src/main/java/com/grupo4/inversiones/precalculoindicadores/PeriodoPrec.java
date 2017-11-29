package com.grupo4.inversiones.precalculoindicadores;

public class PeriodoPrec {
	long id;
	double valor;
	
	
	
	public PeriodoPrec(long id, int valor) {
		super();
		this.id = id;
		this.valor = valor;
	}
	public PeriodoPrec() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
