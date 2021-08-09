package com.meli.co.mutantes.dto;

import java.io.Serializable;

public class MatrizDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int contadorMutante;

	public int getContadorMutante() {
		return contadorMutante;
	}
	
	public void setContadorMutane(int contadorMutante) {
		this.contadorMutante = this.contadorMutante + contadorMutante;
	}

	public MatrizDTO(int contadorMutante) {
		this.contadorMutante = contadorMutante;
	}
}
