package com.grupo4.inversiones.tools;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.grupo4.inversiones.entidades.Empresa;
import com.grupo4.inversiones.entidades.Indicador;
import com.grupo4.inversiones.entidades.Metodologia;
import com.grupo4.inversiones.entidades.condiciones.CondicionFiltro;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;
import com.grupo4.inversiones.entidades.condiciones.ConsistenciaCreciente;
import com.grupo4.inversiones.entidades.condiciones.ConsistenciaDecreciente;
import com.grupo4.inversiones.entidades.condiciones.FiltroMayor;
import com.grupo4.inversiones.entidades.condiciones.FiltroMenor;
import com.grupo4.inversiones.entidades.condiciones.Maximizar;
import com.grupo4.inversiones.entidades.condiciones.Minimizar;
import com.grupo4.inversiones.entidades.condiciones.OrdenMayor;
import com.grupo4.inversiones.entidades.condiciones.OrdenMenor;
import com.grupo4.inversiones.precalculoindicadores.EmpresaPrec;
import com.grupo4.inversiones.precalculoindicadores.IndicadorPrec;
import com.grupo4.inversiones.precalculoindicadores.PeriodoPrec;

public class CargadorDeArchivos {
	
	public static List<Empresa> cargarArchivoEmpresas(String path) throws FileNotFoundException {
		List<Empresa> empresas = new ArrayList<Empresa>();
		Type tipoListaEmpresa = new TypeToken<ArrayList<Empresa>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(path));
		empresas = gson.fromJson(reader, tipoListaEmpresa);
		return empresas;
	}
	
	public static List<Indicador> cargarArchivoIndicadores(String path) throws FileNotFoundException {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		Type tipoListaIndicador = new TypeToken<ArrayList<Indicador>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(path));
		indicadores = gson.fromJson(reader, tipoListaIndicador);
		return indicadores;
	}
	
	public static List<Metodologia> cargarArchivoMetodologias(String path) throws FileNotFoundException {
		List<Metodologia> metodologias = new ArrayList<Metodologia>();
		Type tipoListaMetodologia = new TypeToken<ArrayList<Metodologia>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(path));
		metodologias = gson.fromJson(reader, tipoListaMetodologia);
		return metodologias;
	}
	
	public static List<CondicionFiltro> cargarArchivoCondicionesFiltro(String path) throws FileNotFoundException {
		
		List<CondicionFiltro> condicionesFiltro = new ArrayList<CondicionFiltro>();
		
		RuntimeTypeAdapterFactory<CondicionFiltro> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
			    .of(CondicionFiltro.class, "tipo")
			    .registerSubtype(ConsistenciaCreciente.class, "ConsistenciaCreciente")
			    .registerSubtype(ConsistenciaDecreciente.class, "ConsistenciaDecreciente")
			    .registerSubtype(FiltroMayor.class, "FiltroMayor")
			    .registerSubtype(FiltroMenor.class, "FiltroMenor");
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
		
		Type tipoListaCondicionFiltro = new TypeToken<List<CondicionFiltro>>(){}.getType();
		
		JsonReader reader = new JsonReader(new FileReader(path));
		
		condicionesFiltro = gson.fromJson(reader, tipoListaCondicionFiltro);
		
		for (int i = 0; i < condicionesFiltro.size(); i++) {
			condicionesFiltro.get(i).setTipo(condicionesFiltro.get(i).getClass().getSimpleName());
		}
		
		return condicionesFiltro;
	}
	
	public static List<CondicionOrden> cargarArchivoCondicionesOrden(String path) throws FileNotFoundException {

		List<CondicionOrden> condicionesOrden = new ArrayList<CondicionOrden>();
		
		RuntimeTypeAdapterFactory<CondicionOrden> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
			    .of(CondicionOrden.class, "tipo")
			    .registerSubtype(Maximizar.class, "Maximizar")
			    .registerSubtype(Minimizar.class, "Minimizar")
			    .registerSubtype(OrdenMayor.class, "OrdenMayor")
			    .registerSubtype(OrdenMenor.class, "OrdenMenor");
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
		
		Type tipoListaCondicionOrden = new TypeToken<List<CondicionOrden>>(){}.getType();
		
		JsonReader reader = new JsonReader(new FileReader(path));
		
		condicionesOrden = gson.fromJson(reader, tipoListaCondicionOrden);
		
		for (int i = 0; i < condicionesOrden.size(); i++) {
			condicionesOrden.get(i).setTipo(condicionesOrden.get(i).getClass().getSimpleName());
		}
		
		return condicionesOrden;
		
	}
	
	public static void guardarIndicadores(String path ,List<Indicador> indicadores) throws IOException {
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i <= indicadores.size() - 1; i++){
			JSONObject obj = new JSONObject();
			obj.put("idIndicador", indicadores.get(i).getIdIndicador());
			obj.put("formula", indicadores.get(i).getformula());
			array.add(obj);
		}

	    try (FileWriter file = new FileWriter(path)) {


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(array.toJSONString());
            String prettyJsonString = gson.toJson(je);
            //System.out.println(prettyJsonString);                  

            file.write(prettyJsonString);
            file.flush();
            file.close();
}
		
	}
	
	public static void guardarMetodologias(String path ,List<Metodologia> metodologias) throws IOException {
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < metodologias.size(); i++){
			
			List<String> condicionesFiltroActual = metodologias.get(i).getCondicionesFiltro();
			List<String> condicionesOrdenActual = metodologias.get(i).getCondicionesOrden();
			
			JSONArray condicionesFiltro = new JSONArray();
			JSONArray condicionesOrden = new JSONArray();
			
			for (int j = 0; j < condicionesFiltroActual.size(); j++){
				condicionesFiltro.add(condicionesFiltroActual.get(j));
			}
			
			for (int j = 0; j < condicionesOrdenActual.size(); j++){
				condicionesOrden.add(condicionesOrdenActual.get(j));
			}
			
			
			JSONObject obj = new JSONObject();
			obj.put("nombre", metodologias.get(i).getNombre());
			obj.put("condicionesOrden", condicionesOrden);
			obj.put("condicionesFiltro", condicionesFiltro);
			array.add(obj);
		}

	    try (FileWriter file = new FileWriter(path)) {


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(array.toJSONString());
            String prettyJsonString = gson.toJson(je);
            //System.out.println(prettyJsonString);                  

            file.write(prettyJsonString);
            file.flush();
            file.close();
}
		
	}
	
	public static void guardarCondicionesFiltro(String path ,List<CondicionFiltro> condiciones) throws IOException {
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i <= condiciones.size() - 1; i++){
			JSONObject obj = new JSONObject();
			obj.put("nombreCondicion", condiciones.get(i).getNombreCondicion());
			obj.put("nombreIndicador", condiciones.get(i).getNombreIndicador());
			obj.put("periodo", condiciones.get(i).getPeriodo());
			obj.put("inicioIntervalo", condiciones.get(i).getInicioIntervalo());
			obj.put("finalIntervalo", condiciones.get(i).getFinalIntervalo());
			obj.put("comparador", condiciones.get(i).getComparador());
			obj.put("tipo", condiciones.get(i).getTipo());
			array.add(obj);
		}

	    try (FileWriter file = new FileWriter(path)) {


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(array.toJSONString());
            String prettyJsonString = gson.toJson(je);
            //System.out.println(prettyJsonString);                  

            file.write(prettyJsonString);
            file.flush();
            file.close();
}
	}
	
	public static void guardarCondicionesOrden(String path ,List<CondicionOrden> condiciones) throws IOException {
		
		JSONArray array = new JSONArray();
		
		for (int i = 0; i <= condiciones.size() - 1; i++){
			JSONObject obj = new JSONObject();
			obj.put("nombreCondicion", condiciones.get(i).getNombreCondicion());
			obj.put("nombreIndicador", condiciones.get(i).getNombreIndicador());
			obj.put("periodo", condiciones.get(i).getPeriodo());
			obj.put("inicioIntervalo", condiciones.get(i).getInicioIntervalo());
			obj.put("finalIntervalo", condiciones.get(i).getFinalIntervalo());
			obj.put("importancia", condiciones.get(i).getImportancia());
			obj.put("tipo", condiciones.get(i).getTipo());
			array.add(obj);
		}

	    try (FileWriter file = new FileWriter(path)) {


            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(array.toJSONString());
            String prettyJsonString = gson.toJson(je);                 

            file.write(prettyJsonString);
            file.flush();
            file.close();
	    }
		
	}
	
	public static void guardarIndicadoresPrecalculados(String path ,List<IndicadorPrec> indicadores) throws IOException {
			
			JSONArray arrayIndicadores = new JSONArray();
			
			for (int i = 0; i <= indicadores.size() - 1; i++){
				
				JSONArray arrayEmpresas = new JSONArray();
				
				for(EmpresaPrec e: indicadores.get(i).getEmpresas()) {
					
					JSONArray arrayPeriodos = new JSONArray();
					
					for(PeriodoPrec p: e.getPeriodos()) {
						JSONObject per = new JSONObject();
						per.put("id", p.getId());
						per.put("valor", p.getValor());
						arrayPeriodos.add(per);
					}
					
					
					JSONObject emp = new JSONObject();
					emp.put("id", e.getId());
					emp.put("periodos", arrayPeriodos);
					arrayEmpresas.add(emp);
				}
				
				
				JSONObject obj = new JSONObject();
				obj.put("nombre", indicadores.get(i).getNombre());
				obj.put("id", indicadores.get(i).getId());
				obj.put("empresas", arrayEmpresas);
				arrayIndicadores.add(obj);
			}
	
		    try (FileWriter file = new FileWriter(path)) {
	
	
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            JsonParser jp = new JsonParser();
	            JsonElement je = jp.parse(arrayIndicadores.toJSONString());
	            String prettyJsonString = gson.toJson(je);
	            file.write(prettyJsonString);
	            file.flush();
	            file.close();
		    }
			
	}

	public static List<IndicadorPrec> cargarArchivoIndicadoresPrecalculados(String path) throws FileNotFoundException {
		List<IndicadorPrec> indicadoresPrec = new ArrayList<IndicadorPrec>();
		Type tipoListaIndicadorPrec = new TypeToken<ArrayList<IndicadorPrec>>() {
		}.getType();
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader(path));
		indicadoresPrec = gson.fromJson(reader, tipoListaIndicadorPrec);
		return indicadoresPrec;
	}
	
}