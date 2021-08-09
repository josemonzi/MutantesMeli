package com.meli.co.mutantes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meli.co.mutantes.exception.BadRequestException;

public class Utilidades {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Utilidades.class);	
	
	public boolean validaCadenaDna(String[] dna) {
		LOGGER.info("Valida cadena DNA: " + Arrays.toString(dna));
		
		if( null == dna || 0 == dna.length)
			throw new BadRequestException(Constantes.MENSAJE_MAL_REQUEST);
		
		Pattern patron = Pattern.compile(Constantes.PATRON);
		int cantidad = dna.length;
		for(int i=0; i<cantidad; i++) {	
			if(dna[i].trim().isEmpty())
				throw new BadRequestException(Constantes.MENSAJE_MAL_REQUEST);
			Matcher combina = patron.matcher(dna[i].toUpperCase());
			if(combina.find())
				return false;			
		}
		return true;
	}
	
	public char[][] crearMatriz(String[] dna) {
		LOGGER.info("Crea matriz de la cadena DNA: " + Arrays.toString(dna));
		
		if(dna == null || dna.length == 0)
			throw new BadRequestException(Constantes.MENSAJE_MAL_REQUEST);
			
		int columna = dna[0].length();
		int fila = dna.length;
		char[][] matrizMutante = new char[fila][columna];
		
		for(int i =0; i< fila; i++) {
			for(int j=0; j<columna; j++) {
				matrizMutante[i][j]= dna[i].toCharArray()[j];
				LOGGER.info("Matriz cadena DNA: " + matrizMutante[i][j] + " posicion fila: " + i
						+ " posicion columna: " + j);
			}
		}	
		return matrizMutante;		 
	}
	
	public String convertirDnaSHA256(String dna ) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(Constantes.ALGORITMO);			
		} 
		catch (NoSuchAlgorithmException e) {			
			LOGGER.error(e.getMessage());
			return null;
		}
		    
		byte[] hash = md.digest(dna.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
}