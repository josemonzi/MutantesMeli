package com.meli.co.mutantes.dto;

import java.io.Serializable;

public final class EstadisticaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private final int count_mutant_dna;
	private final int count_human_dna;
	private final double ratio;
	
	public EstadisticaDTO(int count_mutant_dna, int count_human_dna, double ratio) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = ratio;
	}

	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public int getCount_human_dna() {
		return count_human_dna;
	}

	public double getRatio() {
		return ratio;
	}	
}
