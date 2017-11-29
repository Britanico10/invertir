package com.grupo4.inversiones.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.Balance;
import com.grupo4.inversiones.entidades.Empresa;
import com.grupo4.inversiones.entidades.Indicador;
import com.grupo4.inversiones.precalculoindicadores.EmpresaPrec;
import com.grupo4.inversiones.precalculoindicadores.IndicadorPrec;
import com.grupo4.inversiones.precalculoindicadores.PeriodoPrec;
import com.grupo4.inversiones.repositorio.Repositorio;

public class AplicarIndicadores {

	private static Repositorio repositorio = new Repositorio(App.EM_FACTORY.createEntityManager());
	
	static Calendar cal = Calendar.getInstance(); 
	static int anio = cal.get(Calendar.YEAR);
	
	public static String aplicarIndicadores(Empresa empresa, int periodo, List<Indicador> indicadores){
		final String nuevalinea = System.getProperty("line.separator");
		String resultado = "";
		for (int i = 0; i <= indicadores.size() - 1; i++){
			resultado = resultado + indicadores.get(i).getIdIndicador()
					+": "
					+ indicadores.get(i).getformula()
					+ " = "
					+ getIndicadorPrecalculado(indicadores.get(i).getId(),empresa.getId(),periodo)
					+ nuevalinea;
		}
		return resultado;
	
	}
	
	public static double aplicarIndicador(String nombre, Empresa empresa, int periodo){
		return repositorio.indicadores().buscarPorNombre(nombre).aplicarA(empresa,periodo);
	}
	
	public static double promedioIndicadorEnLapso(String nombre, Empresa empresa, int lapso){
		double n = 0;
		for (int i = anio-1; i > (anio-1 - lapso); i--){
			n = aplicarIndicador(nombre,empresa,i);
	    	}
		return n/lapso;
	}
	
	public static Boolean indicadorEsConsistente(String nombre, Empresa empresa, int lapso){
		double val = 1;
		for (int i = anio-1; i > (anio-1 - lapso); i--){
			val = val * aplicarIndicador(nombre, empresa, i);
	    	}
		return val > 0;
	}
	
	public static IndicadorPrec precalcularIndicador(Indicador indicador, List<Empresa> empresas) {
		IndicadorPrec indicadorPrec = new IndicadorPrec();
		indicadorPrec.setId(indicador.getId());
		indicadorPrec.setNombre(indicador.getIdIndicador());
		
		for(Empresa e: empresas) {
			EmpresaPrec empresaPrec = new EmpresaPrec();
			empresaPrec.setId(e.getId());
			
			for(Balance b: e.getBalances()) {
				PeriodoPrec periodoPrec = new PeriodoPrec();
				periodoPrec.setId(b.getPeriodo());
				periodoPrec.setValor(indicador.aplicarA(e, b.getPeriodo()));
				empresaPrec.agregarPeriodo(periodoPrec);
			}
			indicadorPrec.agregarEmpresa(empresaPrec);
		}
		return indicadorPrec;
	}
	
	public static List<IndicadorPrec> precalculo() throws IOException {
		
		List<Indicador> indicadores = repositorio.indicadores().buscarTodas();
		List<Empresa> empresas = repositorio.empresas().buscarTodas();
		List<IndicadorPrec> indicadoresPrecalculados = new ArrayList<IndicadorPrec>();
		
		for(Indicador i: indicadores) {
			IndicadorPrec indicadorPrec = precalcularIndicador(i, empresas);
			indicadoresPrecalculados.add(indicadorPrec);
		}
		CargadorDeArchivos.guardarIndicadoresPrecalculados(App.DIR_INDPREC, indicadoresPrecalculados);
		App.indicadores = indicadoresPrecalculados;
		return indicadoresPrecalculados;
	}
	
	public static void cargarPrecalculados() throws FileNotFoundException {
		App.indicadores = CargadorDeArchivos.cargarArchivoIndicadoresPrecalculados(App.DIR_INDPREC);
	}
	
	public static double getIndicadorPrecalculado(long id, long idEmpresa, int año) {
		List<PeriodoPrec> periodos = App.indicadores.get((int)id-1).getEmpresas().get((int)idEmpresa-1).getPeriodos();
		PeriodoPrec periodo = Listas.buscarPeriodo(periodos, año);
		if (periodo == null) return 0;
		return periodo.getValor();
	}


}