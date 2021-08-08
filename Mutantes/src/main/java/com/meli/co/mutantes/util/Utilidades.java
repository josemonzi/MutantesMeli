package com.meli.co.mutantes.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilidades {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Utilidades.class);	
	
	public boolean validaCadenaDna(String[] dna) {
		LOGGER.info("Valida cadena DNA");
		Pattern patron = Pattern.compile(Constantes.PATRON);
		int cantidad = dna.length;
		for(int i=0; i<cantidad; i++) {			
			Matcher combina = patron.matcher(dna[i].toUpperCase());
			if(combina.find())
				return false;			
		}
		return true;
	}
	
	public char[][] crearMatriz(String[] dna) {
		LOGGER.info("Crea matriz de la cadena DNA");
		int columna = dna[0].length();
		int fila = dna.length;
		char[][] matrizMutante = new char[fila][columna];
		
		for(int i =0; i< fila; i++) {
			for(int j=0; j<columna; j++) {
				matrizMutante[i][j]= dna[i].toCharArray()[j];
				LOGGER.info("Matriz cadena DNA: " + matrizMutante[i][j]);
			}
		}	
		return matrizMutante;		 
	}
}