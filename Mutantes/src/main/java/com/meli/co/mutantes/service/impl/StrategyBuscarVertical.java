package com.meli.co.mutantes.service.impl;

import com.meli.co.mutantes.service.IStrategyBuscarCadena;

public class StrategyBuscarVertical implements IStrategyBuscarCadena {
	
	public boolean execute(int fila, int columna, Character itemPivote, char[][] matriz) {
		boolean bandera= true;	
		int filaMatriz = matriz.length - 1;
		StringBuilder cadena = new StringBuilder();		
		
		while(bandera) {				
			if(fila < (filaMatriz)) {
				fila++;
			}else {
				bandera = false;
				break;
			}
			if(itemPivote.equals(matriz[fila][columna])) {						
				cadena.append(matriz[fila][columna]);
				if(cadena.length()==3) {
					return true;							
				}
			}else {
				bandera = false;
			}
		}
		return false;
	}
}