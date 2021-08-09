package com.meli.co.mutantes.exception;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

public class ForbiddenExceptionTest {
    @Test
    public void objetoDebeSerIgualASiMismo() {
        final ForbiddenException object = new ForbiddenException(HttpStatus.MULTI_STATUS);
        final boolean result = object.equals(object);

        assertTrue(result);
    }

    @Test
    public void objetoNoDebeSerIgualANUll() {
        final ForbiddenException object = new ForbiddenException(HttpStatus.MULTI_STATUS);
        final boolean result = object.equals(null);

        assertFalse(result);
    }


    @Test
    public void objetoNoDebeSerIgualAUnTipoDiferente() {
        final ForbiddenException object = new ForbiddenException(HttpStatus.MULTI_STATUS);
        final boolean result = object.equals("");

        assertFalse(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final ForbiddenException object = new ForbiddenException(HttpStatus.MULTI_STATUS);

        final int result1 = object.hashCode();
        final int result2 = object.hashCode();

        assertEquals(result1, result2);
    }

    @Test
    public void objetoDebeDeRetornarElMismoValorDelContructor() {
        final ForbiddenException object = new ForbiddenException(HttpStatus.MULTI_STATUS);
        object.setStatusCode(HttpStatus.MULTI_STATUS);

        assertTrue(object.getStatusCode() .equals(HttpStatus.MULTI_STATUS));
    }

}