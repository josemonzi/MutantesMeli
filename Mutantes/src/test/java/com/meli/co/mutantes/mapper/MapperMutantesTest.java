package com.meli.co.mutantes.mapper;

import org.junit.Before;
import org.junit.Test;

import com.meli.co.mutantes.exception.ModeloNotFoundException;
import com.meli.co.mutantes.mapper.impl.MapperMutantes;


public class MapperMutantesTest {
	private MapperMutantes mapperMutantes;
	
	 @Before
	    public void setUp() {     

		 mapperMutantes = new MapperMutantes();
	    }

	@Test(expected =  ModeloNotFoundException.class)
	public void cuandoLlegaConsultaBDEntoncesValidaSiLlegaNull()throws Exception {
		mapperMutantes.obtenerEstadisticas(null);
	}
	
	
}
