package com.meli.co.mutantes.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

public class ModeloNotFoundExceptionTest {
    @Test
    public void objetoDebeSerIgualASiMismo() {
        final ModeloNotFoundException object = new ModeloNotFoundException("");
        final boolean result = object.equals(object);

        assertTrue(result);
    }

    @Test
    public void objetoNoDebeSerIgualANUll() {
        final ModeloNotFoundException object = new ModeloNotFoundException("");
        final boolean result = object.equals(null);

        assertFalse(result);
    }

    @Test
    public void objetoNoDebeSerIgualAUnTipoDiferente() {
        final ModeloNotFoundException object = new ModeloNotFoundException("");
        final boolean result = object.equals("");

        assertFalse(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final ModeloNotFoundException object = new ModeloNotFoundException("");

        final int result1 = object.hashCode();
        final int result2 = object.hashCode();

        assertEquals(result1, result2);
    }

    @Test
    public void objetoDebeDeRetornarElMismoValorDelContructor() {
        String valor = "PROBANDO";
        final ModeloNotFoundException object = new ModeloNotFoundException(valor);
        object.setError(valor);
        object.setStatusCode(HttpStatus.MULTI_STATUS);

        assertTrue(object.getError() .equals(valor));
        assertTrue(object.getStatusCode().equals(HttpStatus.MULTI_STATUS));
    }

    @Test
    public void objetoDebeDeRetornarUnHashCodeDiferenteSiSeCreanDosObjetosConDiferentesValores() {
        String valor = "PROBANDO";
        final ModeloNotFoundException object = new ModeloNotFoundException(valor);

        valor = "TEST 32";
        final ModeloNotFoundException object2 = new ModeloNotFoundException(valor, HttpStatus.MULTI_STATUS);

        final int result1 = object.hashCode();
        final int result2 = object2.hashCode();

        assertNotEquals(result1, result2);
    }
}