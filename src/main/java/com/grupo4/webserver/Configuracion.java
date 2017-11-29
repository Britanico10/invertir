package com.grupo4.webserver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
//@EnableWebMvc
public class Configuracion extends WebMvcConfigurerAdapter {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
              
                registry.addMapping("/**")
                .allowedHeaders("*")
        		.allowedOrigins("*")
        		.allowedMethods("PUT", "DELETE", "POST", "GET", "OPTIONS")
        		.allowCredentials(true).maxAge(3600);
                
            }


}
