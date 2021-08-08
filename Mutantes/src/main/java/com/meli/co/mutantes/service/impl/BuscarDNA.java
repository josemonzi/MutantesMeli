package com.meli.co.mutantes.service.impl;

import com.meli.co.mutantes.service.IStrategyBuscarCadena;

public class BuscarDNA {
	private IStrategyBuscarCadena strategyBuscarCadena;	

	public void setStrategyBuscarCadena(IStrategyBuscarCadena strategyBuscarCadena) {
		this.strategyBuscarCadena = strategyBuscarCadena;
	}
	
	public boolean ejecutarStrategy(int fila, int columna, Character itemPivote, char[][] matriz) {
		return strategyBuscarCadena.execute(fila, columna, itemPivote, matriz);
	}
}
