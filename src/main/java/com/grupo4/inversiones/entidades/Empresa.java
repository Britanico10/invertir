package com.grupo4.inversiones.entidades;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.grupo4.inversiones.persistencia.Persistible;
import com.grupo4.inversiones.tools.Listas;

@Entity
@Table(name = "EMPRESA")
public class Empresa extends Persistible implements Comparable<Empresa>{

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int inicioActividad;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmpresa", referencedColumnName = "id") 
	private List<Balance> balances = new ArrayList<Balance>();
	
	private double rentabilidad= 0;
	
	public Empresa() {
	}
	
	public Empresa(String _nombre) {
		this.nombre = _nombre;
	}
	
	public Empresa(String _nombre,int _inicioActividad) {
		nombre = _nombre;
		inicioActividad = _inicioActividad;
	}
	
	@Column(name = "inicioActividad")
	public int getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(int inicioActividad) {
		this.inicioActividad = inicioActividad;
	}
	
	public void agregarElemento(Balance balance){
		balances.add(balance);
	}
	
	@Column(name = "nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Balance> getBalances(){
		return balances;
	}
	
	public void setBalances(List<Balance> balances){
		this.balances = balances;
	}
	
	public String mostrarBalances(int periodo){
		Balance balance = Listas.buscarCuentaEn(balances,periodo);
		String res = "BALANCES PARA EL PERIODO " + periodo + ":"
		+ balance.mostrarse();
		return res;
	}
	
	public String mostrarBalances(){
		final String nuevalinea = System.getProperty("line.separator");
		String res;
		res = "BALANCES:";
		
		for (int i = 0; i <= balances.size() - 1; i++){
			res = res + nuevalinea + "Periodo: " + balances.get(i).getPeriodo()
			+ nuevalinea
			+ balances.get(i).mostrarse()
			+ nuevalinea;
		}
		
		return res;
	}
	
	@Column(name = "rentabilidad")
	public double getRentabilidad() {
		return rentabilidad;
	}

	public void setRentabilidad(double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}
	
	public String toString(){
		final String nuevalinea = System.getProperty("line.separator");
		String res;
		res = "Nombre de la empresa: " + this.getNombre()
		+ nuevalinea
		+ "Inicio  de actividad: " + this.getInicioActividad()
		+ nuevalinea
		+
		this.mostrarBalances()
		+ nuevalinea;
		return res;
	}
	
	public int compareTo(Empresa empresa) {

		double rentabilidadDeComparacion = empresa.getRentabilidad();

		return (int)(rentabilidadDeComparacion - this.rentabilidad);

	}
	
}
