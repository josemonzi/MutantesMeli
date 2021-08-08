package com.meli.co.mutantes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.meli.co.mutantes.entities.Mutante;

public interface IMutanteDao extends JpaRepository<Mutante, Integer>{
	
	@Query(value= "select (select count(m.dna) from mutante m where m.ctrMutante = 1) as \"count_mutant_dna\" ," + 
			"(select count(m.dna) from mutante m where m.ctrMutante = 0) as \"count_human_dna\"," + 
			"(select count(m.dna) from mutante m where m.ctrMutante = 1)/ (select count(m.dna) from mutante m where m.ctrMutante = 0)" + 
			"from dual", nativeQuery = true)
	String consultaRegistros();
}
