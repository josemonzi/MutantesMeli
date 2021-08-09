package com.meli.co.mutantes.service.impl;

import com.meli.co.mutantes.dto.MatrizDTO;
import com.meli.co.mutantes.service.IStrategyBuscarCadena;

public class BuscarDNA {
	private IStrategyBuscarCadena strategyBuscarCadena;	

	public void setStrategyBuscarCadena(IStrategyBuscarCadena strategyBuscarCadena) {
		this.strategyBuscarCadena = strategyBuscarCadena;
	}
	
	public char[][] ejecutarStrategy(int fila, int columna, Character itemPivote, char[][] matriz, MatrizDTO cantidadMutante) {
		return strategyBuscarCadena.execute(fila, columna, itemPivote, matriz, cantidadMutante );
	}
}
