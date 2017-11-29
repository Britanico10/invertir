package com.grupo4.inversiones.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.persistencia.Persistible;
import com.grupo4.inversiones.repositorio.Repositorio;
import com.grupo4.inversiones.tools.PrintEmpresas;
import com.grupo4.inversiones.tools.Rentabilidad;

import edu.emory.mathcs.backport.java.util.Collections;

@Entity
@Table(name = "METODOLOGIA")
public class Metodologia extends Persistible{

	@Transient
	private static final long serialVersionUID = 1L;
	
	private String nombre; 
	private long duenio;
	
	@ElementCollection
	@CollectionTable(name="condicionesOrden", joinColumns=@JoinColumn(name = "idMetodologia", referencedColumnName = "id"))
	@Column(name="condicionOrden")
	private List<String> condicionesOrden = new ArrayList<String>();
	
	@ElementCollection
	@CollectionTable(name="condicionesFiltro", joinColumns=@JoinColumn(name = "idMetodologia", referencedColumnName = "id"))
	@Column(name="condicionFiltro")
	private List<String> condicionesFiltro = new ArrayList<String>();
	
	public Metodologia(String nombre, long duenio, List<String> condicionesOrden, List<String> condicionesFiltro) {
		this.nombre = nombre;
		this.duenio = duenio;
		this.condicionesFiltro = condicionesFiltro;
		this.condicionesOrden = condicionesOrden;
	}
	
	public Metodologia() {
	}
	
	public List<String> getCondicionesOrden() {
		return condicionesOrden;
	}

	public void setCondicionesOrden(List<String> condicionesOrden) {
		this.condicionesOrden = condicionesOrden;
	}

	public List<String> getCondicionesFiltro() {
		return condicionesFiltro;
	}

	public void setCondicionesFiltro(List<String> condicionesFiltro) {
		this.condicionesFiltro = condicionesFiltro;
	}

	public String getNombre() {
		return nombre;
	}
	
	@Column(name="duenio")
	public long getDuenio() {
		return duenio;
	}

	public void setDuenio(long duenio) {
		this.duenio = duenio;
	}

	public List<Empresa> aplicarCondicionesFiltro(List<Empresa> empresas) throws Exception {
		
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		
		List<Empresa> empresasFiltradas = empresas;
		for(int i = 0; i < condicionesFiltro.size(); i++) {
			CondicionFiltro condicionBuscada = repositorio.condicionesFiltro().buscarPorNombre(condicionesFiltro.get(i));
			if(condicionBuscada == null) {
				throw new Exception("No se pudo encontrar la condición " + condicionesFiltro.get(i));
			}
			empresasFiltradas = condicionBuscada.filtrar(empresasFiltradas);
		}
		repositorio.cerrar();
		return empresasFiltradas;
	}
	
	public void aplicarCondicionesDeOrden(List<Empresa> empresas) throws Exception {
		
		Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
		
		Rentabilidad.inicializarRentabilidad(empresas);
		for(int i = 0; i < condicionesOrden.size(); i++) {
			CondicionOrden condicionBuscada = repositorio.condicionesOrden().buscarPorNombre(condicionesOrden.get(i));
			if(condicionBuscada == null) {
				throw new Exception("No se pudo encontrar la condición " + condicionesFiltro.get(i));
			}
			condicionBuscada.evaluar(empresas);
		}
		Collections.sort(empresas);
	}
	
	public String aplicarMetodologiaATodas(List<Empresa> empresas) throws Exception{
		
		List<Empresa> empresasFiltradas = aplicarCondicionesFiltro(empresas);
		aplicarCondicionesDeOrden(empresasFiltradas);
		
		return PrintEmpresas.imprimirResultado(empresasFiltradas);
		
	}
	
	public void agregarCondicionFiltro(CondicionFiltro condicion) {
		condicionesFiltro.add(condicion.getNombreCondicion());
	}
	
	public void agregarCondicionOrden(CondicionOrden condicion) {
		condicionesOrden.add(condicion.getNombreCondicion());
	}
	
	public void setNombre(String _nombre) {
		nombre = _nombre;
	}
	
	
}
