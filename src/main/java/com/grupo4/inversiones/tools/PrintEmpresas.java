package com.grupo4.inversiones.tools;

import java.util.List;

import com.grupo4.inversiones.entidades.Empresa;

public class PrintEmpresas {

	public static void mostrarEmpresas(List<Empresa> empresas){
		for (int i = 0; i <= empresas.size() - 1; i++){
			System.out.println(empresas.get(i).toString());
		}
	}
	
	public static String imprimirResultado(List<Empresa> empresas){
		
		final String nuevalinea = System.getProperty("line.separator");
		String resultado = "";
		
		   for (int i = 0; i < empresas.size(); i++){
		    	resultado = resultado +
		    			"Empresa: " + empresas.get(i).getNombre() + nuevalinea +
		    			"Rentabilidad de inversión: " + empresas.get(i).getRentabilidad() + nuevalinea +
		   				nuevalinea;
		    }
	     return resultado;
	}
	
}
