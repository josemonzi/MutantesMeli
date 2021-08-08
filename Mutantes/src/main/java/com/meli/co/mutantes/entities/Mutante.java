package com.meli.co.mutantes.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mutante database table.
 * 
 */
@Entity
@NamedQuery(name="Mutante.findAll", query="SELECT m FROM Mutante m")
public class Mutante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idMutante;

	private int ctrMutante;

	private String dna;

	public Mutante() {
	}

	public int getIdMutante() {
		return this.idMutante;
	}

	public void setIdMutante(int idMutante) {
		this.idMutante = idMutante;
	}

	public int getCtrMutante() {
		return this.ctrMutante;
	}

	public void setCtrMutante(int ctrMutante) {
		this.ctrMutante = ctrMutante;
	}

	public String getDna() {
		return this.dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

}