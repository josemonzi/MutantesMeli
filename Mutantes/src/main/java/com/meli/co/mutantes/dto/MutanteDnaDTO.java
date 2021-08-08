package com.meli.co.mutantes.dto;

import java.io.Serializable;

public class MutanteDnaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}

	public MutanteDnaDTO(String[] dna) {
		super();
		this.dna = dna;
	}

	public MutanteDnaDTO() {
		super();
	}		
}