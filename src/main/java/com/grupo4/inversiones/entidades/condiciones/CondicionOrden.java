package com.grupo4.inversiones.entidades.condiciones;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.grupo4.inversiones.entidades.Empresa;


@Entity
@Table(name="CONDICION_ORDEN")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",discriminatorType=DiscriminatorType.STRING)
@AttributeOverride(name = "tipo", column = @javax.persistence.Column(name="tipo", nullable=false, insertable = false, updatable = false))
public abstract class CondicionOrden extends Condicion {
	
	private static final long serialVersionUID = 1L;
	int importancia;
	
	public CondicionOrden() {
	}
	
	public int getImportancia() {
		return importancia;
	}

	public void setImportancia(int importancia) {
		this.importancia = importancia;
	}

	public CondicionOrden(String _nombreCondicion, int _inicioIntervalo, int _finalIntervalo, String _indicador, int _periodo, int _importancia) {
		super(_nombreCondicion, _inicioIntervalo, _finalIntervalo, _indicador, _periodo);
		importancia = _importancia;
	}
	
	public void evaluar(List<Empresa> empresas) {
		ordenarPorIndicador(empresas);
		calcularRentabilidad(empresas);
	}
	
	public void ordenarPorIndicador(List<Empresa> empresas) {}
	
	public void calcularRentabilidad(List<Empresa> empresas) {
		for(int i = 0; i < empresas.size(); i++) {
			empresas.get(i).setRentabilidad(i*importancia);
		}
	}

}
