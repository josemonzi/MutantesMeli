package com.meli.co.mutantes.service;

public interface IStrategyBuscarCadena {
	boolean execute(int fila, int columna, Character itemPivote, char[][] matriz);
}
