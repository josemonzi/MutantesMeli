package com.meli.co.mutantes.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class EstadisticaDTOTest {	

	@Test
    public void objetoDebeSerIgualASiMismo() {
        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(0, 0, 0.0);
        final boolean result = estadisticaDto.equals(estadisticaDto);

        assertTrue(result);
    }

    @Test
    public void objetoDebeSerIgualValorAsignadoConteoHumano() {
        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(0, 0, 0.0);
                
        final boolean result = 0 == estadisticaDto.getCount_mutant_dna();
        assertTrue(result);
    }


    @Test
    public void objetoDebeSerIgualValorAsignadoConteoMutante() {
        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(0, 1, 0.0);
                
        final boolean result = 1 == estadisticaDto.getCount_human_dna();
        assertTrue(result);
    }
    
    @Test
    public void objetoDebeSerIgualValorAsignadoProporcion() {
        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(50, 100, 0.5);
                
        final boolean result = 0.5 == estadisticaDto.getRatio();
        assertTrue(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(1,2,0.2);

        final int result1 = estadisticaDto.hashCode();
        final int result2 = estadisticaDto.hashCode();

        assertEquals(result1, result2);
    }   

    @Test
    public void objetoDebeDeRetornarUnHashCodeDiferenteSiSeCreanDosObjetosConDiferentesValores() { 

        final EstadisticaDTO estadisticaDto = new EstadisticaDTO(50, 100, 0.5);
        final EstadisticaDTO estadisticaDto2 = new EstadisticaDTO(60, 100, 0.6);
        
        final int result1 = estadisticaDto.hashCode();
        final int result2 = estadisticaDto2.hashCode();

        assertNotEquals(result1, result2);
    }
}
