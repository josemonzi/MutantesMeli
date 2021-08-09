package com.meli.co.mutantes.service.impl;

import java.util.Arrays;

import com.meli.co.mutantes.dto.MatrizDTO;
import com.meli.co.mutantes.service.IStrategyBuscarCadena;

public class StrategyBuscarVertical implements IStrategyBuscarCadena {
	
	public char[][] execute(int fila, int columna, Character itemPivote, char[][] matriz, MatrizDTO cantidadMutante) {
		boolean bandera= true;	
		int filaMatriz = matriz.length - 1,
			filaOrigen = fila;		
		char[][] matrizTmp = Arrays.stream(matriz)
				.map(r -> r.clone()).toArray(char[][]::new);
		StringBuilder cadena = new StringBuilder();		
		
		while(bandera) {			
			if(fila < (filaMatriz)) {
				fila++;
			}else {				
				break;
			}
			if(itemPivote.equals(matriz[fila][columna])) {						
				cadena.append(matriz[fila][columna]);
				matrizTmp[fila][columna] = 'M';
				if(cadena.length()==3) {
					matrizTmp[filaOrigen][columna] = 'M';	
					cantidadMutante.setContadorMutane(1);
					return matrizTmp;
				}
			}else {
				break;
			}
		}
		return matriz;
	}
}