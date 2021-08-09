package com.meli.co.mutantes.service;

import com.meli.co.mutantes.dto.MatrizDTO;

public interface IStrategyBuscarCadena {
	char[][] execute(int fila, int columna, Character itemPivote, char[][] matriz, MatrizDTO cantidadMutante);
}
