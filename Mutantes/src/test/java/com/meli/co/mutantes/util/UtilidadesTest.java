package com.meli.co.mutantes.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.meli.co.mutantes.exception.BadRequestException;


public class UtilidadesTest {
	
	private Utilidades utilidades;
	
	@Before
	public void setUp() {
		utilidades = new Utilidades();
	}

	@Test
	public void cuandoLlegaCadenaMutanteValidaEntoncesValidaCadenaDNARetornaVerdadero() {
		String[] dna = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		final boolean resultado =  utilidades.validaCadenaDna(dna);
		
		assertTrue(resultado);
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteNoValidaEntoncesValidaCadenaDNARetornaFalso() {
		String[] dna = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTD"};
		final boolean resultado =  utilidades.validaCadenaDna(dna);
		
		assertFalse(resultado);
	}
	
	@Test(expected = BadRequestException.class)
	public void cuandoLlegaCadenaMutanteNulaNoValidaEntoncesValidaCadenaDNAError400()throws Exception {
		String[] dna = new String[] {};
		
		utilidades.crearMatriz(dna);
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaEntoncesCreaMatrizRetornandoMatrizNXN() {
		String[] dna = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		int columna = dna[0].length();
		int fila = dna.length;
		char[][] matrizMutante = new char[fila][columna];
		for(int i =0; i< fila; i++) {
			for(int j=0; j<columna; j++) {
				matrizMutante[i][j]= dna[i].toCharArray()[j]; 
			}
		}
		
		final char[][] resultado =  utilidades.crearMatriz(dna);
		
		assertTrue(resultado.length > 0);
		assertArrayEquals(matrizMutante, resultado);
	}
	
	@Test(expected = BadRequestException.class)
	public void cuandoLlegaCadenaMutanteNulaNoValidaEntoncesCreaMatrizRetornandoError400()throws Exception {
		String[] dna = new String[] {};
		
		utilidades.crearMatriz(dna);
	}
	
	@Test(expected = BadRequestException.class)
	public void cuandoLlegaCadenaMutanteNullNoValidaEntoncesCreaMatrizRetornandoError400()throws Exception {
		String[] dna = null;
		
		utilidades.crearMatriz(dna);
	}

}
