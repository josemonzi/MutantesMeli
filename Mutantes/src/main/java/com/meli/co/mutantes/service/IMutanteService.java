package com.meli.co.mutantes.service;

import java.util.List;

import com.meli.co.mutantes.entities.Mutante;

public interface IMutanteService {
	boolean isMutant(String[] dna);
	List<Mutante> obtenerEstadisticas();
}
