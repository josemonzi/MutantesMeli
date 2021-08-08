package com.meli.co.mutantes.service.impl;

import com.meli.co.mutantes.service.IStrategyBuscarCadena;

public class StrategyBuscarDiagonalDerecha implements IStrategyBuscarCadena{

	@Override
	public boolean execute(int fila, int columna, Character itemPivote, char[][] matriz) {
		boolean bandera= true;	
		int columnaMatriz =  matriz[0].length -1;
		int filaMatriz = matriz.length - 1;
		StringBuilder cadena = new StringBuilder();		
		
		while(bandera) {
			if (columna < (columnaMatriz)) {
				columna++;
			}else {
				bandera = false;
				break;
			}				
			if(fila < (filaMatriz)) {
				fila++;
			}else {
				bandera = false;
				break;
			}
			if(itemPivote.equals(matriz[fila][columna])){
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