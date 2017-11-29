package com.grupo4.inversiones.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.grupo4.inversiones.persistencia.Persistible;

@Entity
//@Table(name = "BALANCE", indexes = { @Index(columnList = "idEmpresa") })
@Table(name = "BALANCE")
@NamedQuery(name="buscarBalances", query="SELECT b FROM Balance b WHERE b.idEmpresa = :empresaID") 
public class Balance extends Persistible{

	private static final long serialVersionUID = 1L;
	private long idEmpresa;
	private int periodo = 0;
	private int ebitda = 0;
	private int fds = 0;
	private int fCashFlow = 0;
	private int ingNetoOpCont = 0;
	private int ingNetoOpDiscont = 0;
	private int deuda = 0;
	private int capitalPropio = 0;
	
	public Balance() {
		
	}
	
	public Balance(int _periodo, int _ebitda, int _fds, int _fCashFlow, int _ingNetoOpCont, int _ingNetoOpDiscont, int _deuda, int _capitalPropio) {
		periodo = _periodo;
		ebitda = _ebitda;
		fds  = _fds;
		fCashFlow = _fCashFlow;
		ingNetoOpCont = _ingNetoOpCont;
		ingNetoOpDiscont = _ingNetoOpDiscont;
		deuda = _deuda;
		capitalPropio = _capitalPropio;
	}
	
	public Balance(int _periodo) {
		periodo = _periodo;
	}
	
	@Column(name = "deuda")
	public int getDeuda() {
		return deuda;
	}
	
	public void setDeuda(int deuda) {
		this.deuda = deuda;
	}
	
	@Column(name = "capitalPropio")
	public int getCapitalPropio() {
		return capitalPropio;
	}
	
	public void setCapitalPropio(int capitalPropio) {
		this.capitalPropio = capitalPropio;
	}
	
	@Column(name = "ebitda")
	public int getEbitda() {
		return ebitda;
	}
	
	public void setEbitda(int ebitda) {
		this.ebitda = ebitda;
	}
	
	@Column(name = "fds")
	public int getFds() {
		return fds;
	}
	
	public void setFds(int fds) {
		this.fds = fds;
	}
	
	@Column(name = "fCashFlow")
	public int getfCashFlow() {
		return fCashFlow;
	}
	
	public void setfCashFlow(int fCashFlow) {
		this.fCashFlow = fCashFlow;
	}
	
	@Column(name = "ingNetoOpCont")
	public int getIngNetoOpCont() {
		return ingNetoOpCont;
	}
	
	public void setIngNetoOpCont(int ingNetoOpCont) {
		this.ingNetoOpCont = ingNetoOpCont;
	}
	
	@Column(name = "ingNetoOpDiscont")
	public int getIngNetoOpDiscont() {
		return ingNetoOpDiscont;
	}
	
	public void setIngNetoOpDiscont(int ingNetoOpDiscont) {
		this.ingNetoOpDiscont = ingNetoOpDiscont;
	}
	
	@Column(name = "periodo")
	public int getPeriodo(){
		return periodo;
	}
	
	public void setPeriodo(int _periodo){
		periodo = _periodo;
	}
	
	@Column(name = "idEmpresa")
	public long getIdEmpresa(){
		return idEmpresa;
	}
	
	public void setIdEmpresa(int _idEmpresa){
		idEmpresa = _idEmpresa;
	}
	public String mostrarse(){
		final String nuevalinea = System.getProperty("line.separator");
		String res = "Deuda: " + this.deuda
		+ nuevalinea
		+ "Ebitda: " + this.ebitda
		+ nuevalinea
		+ "fCashFlow: " + this.fCashFlow
		+ nuevalinea
		+ "Fds: " + this.fds
		+ nuevalinea
		+ "IngNetoOpCont: " + this.ingNetoOpCont
		+ nuevalinea
		+ "IngNetOpDiscont: " + this.ingNetoOpDiscont
		+ nuevalinea
		+ "capitalPropio: " + this.capitalPropio
		+ nuevalinea
		+ " ";
		return res;
	}
	

}
