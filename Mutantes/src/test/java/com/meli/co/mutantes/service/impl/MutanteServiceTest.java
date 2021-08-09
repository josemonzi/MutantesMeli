package com.meli.co.mutantes.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.entities.Mutante;
import com.meli.co.mutantes.exception.ForbiddenException;
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
		String[] dna = new String[] {"ATGCCA","CAGTGC","TTATGT","AGAAGG","CTCCGA","TCACTA"};
		final boolean resultado =  mutanteService.isMutant(dna);
		
		assertTrue(resultado);
	}
	
	@Test(expected = ForbiddenException.class)
	public void cuandoLlegaCadenaMutanteNoValidaEntoncesValidaSiEsMutanteRetornado403() throws Exception {
		String[] dna = new String[] {"ATG","CAG","TTA", "AGA"};
		mutanteService.isMutant(dna);	
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaEntoncesValidaSiEsMutanteRetornadoVerdaderoRetornado200() throws Exception {		
		String[] dna = new String[] {"AAAAGA","GGGGGC","TATTCT","AGACTA","CCCTTA","TCTTTT"};
		final boolean resultado =  mutanteService.isMutant(dna);
		assertTrue(resultado);
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteYaExistenteEnBDEntoncesNoGuardaEnBD() throws Exception {
		String[] dna = new String[] {"AAAAGA","GGGGGC","TATTCT","AGACTA","CCCTTA","TCTTTT"};
		
		Mutante m = new Mutante();
		m.setCtrMutante(1);
		m.setDna("abc");
		m.setIdMutante(1);
		
		when(mutanteDao.findByDna("f3e6f2956e2eac2580460160eaf59a33a44fa67c24bc29a60dd2d115dc86d41c")).thenReturn(m);
		final boolean resultado =  mutanteService.isMutant(dna);
		assertTrue(resultado);
	}
}
