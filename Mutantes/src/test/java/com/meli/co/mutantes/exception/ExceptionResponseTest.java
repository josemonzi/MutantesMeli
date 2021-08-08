package com.meli.co.mutantes.exception;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ExceptionResponseTest {
    @Test
    public void objetoDebeSerIgualASiMismo() {
        final ExceptionResponse object = new ExceptionResponse(new Date(),"","");
        final boolean result = object.equals(object);

        assertTrue(result);
    }

    @Test
    public void objetoNoDebeSerIgualANUll() {
        final ExceptionResponse object = new ExceptionResponse(new Date(),"","");
        final boolean result = object.equals(null);

        assertFalse(result);
    }


    @Test
    public void objetoNoDebeSerIgualAUnTipoDiferente() {
        final ExceptionResponse object = new ExceptionResponse(new Date(),"","");
        final boolean result = object.equals("");

        assertFalse(result);
    }

    @Test
    public void objetoNoDebeTenerElMismoHash() {
        final ExceptionResponse object = new ExceptionResponse(new Date(),"","");

        final int result1 = object.hashCode();
        final int result2 = object.hashCode();

        assertEquals(result1, result2);
    }

    @Test
    public void objetoDebeDeRetornarElMismoValorDelContructor() {
        final Date fechaActual = new Date();
        final String detalle = "Ejemplo de detalle";
        final String mensaje = "Ejemplo de mensaje";
        final ExceptionResponse object = new ExceptionResponse(fechaActual,"","");
        object.setDetalles(detalle);
        object.setMensaje(mensaje);
        object.setTimestamp(fechaActual);

        assertTrue(object.getTimestamp() .equals(fechaActual));
        assertTrue(object.getDetalles() .equals(detalle));
        assertTrue(object.getMensaje() .equals(mensaje));
    }

    @Test
    public void objetoDebeDeRetornarUnHashCodeDiferenteSiSeCreanDosObjetosConDiferentesValores() throws ParseException {
        final Date fechaActual = new Date();
        final ExceptionResponse object = new ExceptionResponse(fechaActual,"","");

        final Date fecha = new SimpleDateFormat("yyyy-mm-dd").parse("2016-01-01");
        final ExceptionResponse object2 = new ExceptionResponse(fecha,"","");

        final int result1 = object.hashCode();
        final int result2 = object2.hashCode();

        assertNotEquals(result1, result2);
    }
}