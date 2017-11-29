package com.grupo4.inversiones.precalculoindicadores;

import java.util.ArrayList;
import java.util.List;

public class EmpresaPrec {
	long id;
	List<PeriodoPrec> periodos;
	
	public EmpresaPrec(long id, List<PeriodoPrec> periodos) {
		super();
		this.id = id;
		this.periodos = periodos;
	}
	
	public EmpresaPrec() {
		periodos = new ArrayList<PeriodoPrec>();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<PeriodoPrec> getPeriodos() {
		return periodos;
	}
	public void setPeriodos(List<PeriodoPrec> periodos) {
		this.periodos = periodos;
	}
	
	public void agregarPeriodo(PeriodoPrec periodo) {
		periodos.add(periodo);
	}

}
