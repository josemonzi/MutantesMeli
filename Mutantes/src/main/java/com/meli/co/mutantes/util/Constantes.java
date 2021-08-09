package com.meli.co.mutantes.util;

public class Constantes {		
	//Controladores
	public static final String MUTANTE_CONTROLLER = "/mutant";
	public static final String ESTADISTICA_CONTROLLER = "/stats";			
		
	//Mensajes Error
    public static final String MENSAJE_SUCCESS_MUTANTE = "La cadena DNA evaluada es de Mutante.";
    public static final String MENSAJE_SUCCESS_HUMANO = "La cadena DNA evaluada es de Humano.";
    public static final String MENSAJE_MAL_REQUEST = "La cadena DNA mal formada";
    public static final String MENSAJE_NULO = "Sin resultados en la consulta"; 
  	
  	//Generales
  	public static final String PATRON = "[^ATCG]";
  	public static final String ALGORITMO = "SHA-256";
}
