package com.meli.co.mutantes.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.meli.co.mutantes.mapper.MapperMutantes;
import com.meli.co.mutantes.mapper.impl.IMapperMutantes;
import com.meli.co.mutantes.service.impl.BuscarDNA;
import com.meli.co.mutantes.util.Utilidades;

@Configuration
@EnableTransactionManagement
public class BeanConfiguracion {
	
	@Bean
	public Utilidades utilidades() {
		return new Utilidades();
	}
	
	@Bean
	public BuscarDNA BuscarDNA() {
		return new BuscarDNA();
	}
	
	@Bean
	public IMapperMutantes mapperMutantes() {
		return new MapperMutantes();
	}
}
