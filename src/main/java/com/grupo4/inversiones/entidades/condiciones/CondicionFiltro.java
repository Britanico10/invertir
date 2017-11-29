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
@Table(name="CONDICION_FILTRO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",discriminatorType=DiscriminatorType.STRING)
@AttributeOverride(name = "tipo", column = @javax.persistence.Column(name="tipo", nullable=false, insertable = false, updatable = false))
public abstract class CondicionFiltro extends Condicion {
	
	private static final long serialVersionUID = 1L;
	int comparador;
	
	public CondicionFiltro(String _nombreCondicion, int _inicioIntervalo, int _finalIntervalo, String _indicador, int _periodo, int _comparador) {
		super(_nombreCondicion, _inicioIntervalo, _finalIntervalo, _indicador, _periodo);
		comparador = _comparador;
	}
	
	public CondicionFiltro() {
	}
	
	public List<Empresa> filtrar(List<Empresa> empresas){
		return empresas;
		}

	public int getComparador() {
		return comparador;
	}

	public void setComparador(int comparador) {
		this.comparador = comparador;
	}
	
}
