package com.grupo4.inversiones.tools;

import java.util.List;

import com.grupo4.inversiones.entidades.Empresa;

public class Rentabilidad {
	public static void inicializarRentabilidad(List<Empresa> empresas){
		for (int i = 0; i <= empresas.size() - 1; i++){
			empresas.get(i).setRentabilidad(0);
		}
	}
}
