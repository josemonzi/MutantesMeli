package com.meli.co.mutantes.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

public class BadRequestExceptionTest {
    @Test
    public void objetoDebeSerIgualASiMismo() {
        final BadRequestException object = new BadRequestException("");
        final boolean result = object.equals(object);

        assertTrue(result);
    }

    @Test
    public void objetoNoDebeSerIgualANUll() {
        final BadRequestException object = new BadRequestException("");
        final boolean result = object.equals(null);

        assertFalse(result);
    }


    @Test
    public void objetoNoDebeSerIgualAUnTipoDiferente() {
        final BadRequestException object = new BadRequestException("");
        final boolean result = object.equals("");

        assertFalse(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final BadRequestException object = new BadRequestException("");

        final int result1 = object.hashCode();
        final int result2 = object.hashCode();

        assertEquals(result1, result2);
    }

    @Test
    public void objetoDebeDeRetornarElMismoValorDelContructor() {
        String valor = "PROBANDO";
        final BadRequestException object = new BadRequestException("");
        object.setError(valor);
        object.setStatusCode(HttpStatus.MULTI_STATUS);

        assertTrue(object.getError() .equals(valor));
        assertTrue(object.getStatusCode() .equals(HttpStatus.MULTI_STATUS));
    }

    @Test
    public void objetoDebeDeRetornarUnHashCodeDiferenteSiSeCreanDosObjetosConDiferentesValores() {
        String valor = "PROBANDO";
        final BadRequestException object = new BadRequestException("");
        object.setError(valor);

        String valor2 = "PROBANDO2";
        final BadRequestException object2 = new BadRequestException("", HttpStatus.MULTI_STATUS);
        object.setError(valor2);

        final int result1 = object.hashCode();
        final int result2 = object2.hashCode();

        assertNotEquals(result1, result2);
    }
}