package com.meli.co.mutantes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.co.mutantes.entities.Mutante;

public interface IMutanteDao extends JpaRepository<Mutante, Integer>{	
	Mutante findByDna(String dna);
}
