package com.grupo4.webserver;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import com.grupo4.inversiones.tools.AplicarIndicadores;
import com.grupo4.inversiones.tools.CargadorDeArchivos;

@SpringBootApplication
@ServletComponentScan
public class WebserverApplication {

	public static void main(String[] args) throws IOException{
		AplicarIndicadores.precalculo();
		SpringApplication.run(WebserverApplication.class, args);
	}
}
