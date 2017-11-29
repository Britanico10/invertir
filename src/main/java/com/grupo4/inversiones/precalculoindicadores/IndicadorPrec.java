package com.grupo4.inversiones.precalculoindicadores;

import java.util.ArrayList;
import java.util.List;

public class IndicadorPrec {
	String nombre;
	long id;
	List<EmpresaPrec> empresas;
	
	public IndicadorPrec(long id, List<EmpresaPrec> empresas, String nombre) {
		this.nombre = nombre;
		this.id = id;
		this.empresas = empresas;
	}
	
	public IndicadorPrec() {
		empresas = new ArrayList<EmpresaPrec>();
	}

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<EmpresaPrec> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<EmpresaPrec> empresas) {
		this.empresas = empresas;
	}
	
	public void agregarEmpresa(EmpresaPrec empresa) {
		empresas.add(empresa);
	}
	

}
