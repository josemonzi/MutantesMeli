package com.meli.co.mutantes.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.service.IMutanteService;
import com.meli.co.mutantes.util.Utilidades;


public class MutanteServiceTest {
	
	private Utilidades utilidades;
	private BuscarDNA buscarDna;
	private IMutanteDao mutanteDao;
	private IMutanteService mutanteService;
	
	
	@Before
	public void setUp() {
		mutanteDao = mock(IMutanteDao.class);
		utilidades = new Utilidades();
		buscarDna = new BuscarDNA();
		
		mutanteService = new MutanteService(utilidades, buscarDna, mutanteDao);
	}

	@Test
	public void cuandoLlegaCadenaMutanteValidaEntoncesValidaSiEsMutanteRetornadoVerdadero() {
		String[] dna = new String[] {"ATGCGA","CGTGGC","TTCTGT","ACAATG","CGCCTA","TCACTC"};
		final boolean resultado =  mutanteService.isMutant(dna);
		
		assertTrue(resultado);
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteNoValidaEntoncesValidaSiEsMutanteRetornadoFalso() {
		String[] dna = new String[] {"ATG","CAG","TTA", "AGA"};
		final boolean resultado =  mutanteService.isMutant(dna);
		assertFalse(resultado);
	}
}
