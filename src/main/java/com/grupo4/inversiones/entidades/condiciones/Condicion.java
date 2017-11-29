package com.grupo4.inversiones.entidades.condiciones;

import javax.persistence.MappedSuperclass;

import com.grupo4.inversiones.persistencia.Persistible;

@MappedSuperclass
public abstract class Condicion extends Persistible{
	
	private static final long serialVersionUID = 1L;
	String nombreCondicion;
	String nombreIndicador;
	int periodo;
	int inicioIntervalo;
	int finalIntervalo;
	String tipo;
	long duenio;

	public Condicion() {
	}

	
	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public long getDuenio() {
		return duenio;
	}

	public void setDuenio(long duenio) {
		this.duenio = duenio;
	}

	public String getNombreIndicador() {
		return nombreIndicador;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public int getInicioIntervalo() {
		return inicioIntervalo;
	}

	public void setInicioIntervalo(int inicioIntervalo) {
		this.inicioIntervalo = inicioIntervalo;
	}

	public int getFinalIntervalo() {
		return finalIntervalo;
	}

	public void setFinalIntervalo(int finalIntervalo) {
		this.finalIntervalo = finalIntervalo;
	}

	public Condicion(String _nombreCondicion, int _inicioIntervalo, int _finalIntervalo, String _nombreIndicador, int _periodo) {
		nombreCondicion = _nombreCondicion;
		inicioIntervalo = _inicioIntervalo;
		finalIntervalo = _finalIntervalo;
		nombreIndicador = _nombreIndicador;
		periodo = _periodo;
	}
}
