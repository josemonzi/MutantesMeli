package com.meli.co.mutantes.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MutanteDnaDTOTest {	

	@Test
    public void objetoDebeSerIgualASiMismo() {
        final MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
        final boolean result = mutanteDnaDto.equals(mutanteDnaDto);

        assertTrue(result);
    }

    @Test
    public void objetoDebeSerIgualValorAsignadoConteoHumano() {
    	String[] dna = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        final MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO(dna);
                
        final boolean result = mutanteDnaDto.getDna().length > 0;
        assertTrue(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();

        final int result1 = mutanteDnaDto.hashCode();
        final int result2 = mutanteDnaDto.hashCode();

        assertEquals(result1, result2);
    }   

    @Test
    public void objetoDebeDeRetornarUnHashCodeDiferenteSiSeCreanDosObjetosConDiferentesValores() { 
    	String[] dna = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        final MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO(dna);
        String[] dna2 = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA"};
        final MutanteDnaDTO mutanteDnaDto2 = new MutanteDnaDTO(dna2);
        
        final int result1 = mutanteDnaDto.hashCode();
        final int result2 = mutanteDnaDto2.hashCode();

        assertNotEquals(result1, result2);
    }
}
