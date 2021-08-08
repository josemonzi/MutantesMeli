package com.meli.co.mutantes.service.impl;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.entities.Mutante;
import com.meli.co.mutantes.exception.BadRequestException;
import com.meli.co.mutantes.service.IMutanteService;
import com.meli.co.mutantes.util.Constantes;
import com.meli.co.mutantes.util.Utilidades;

@Service
public class MutanteService implements IMutanteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MutanteService.class);	
	
	private Utilidades utilidades;
	private BuscarDNA buscarDna;
	private IMutanteDao mutanteDao;
	
	@Autowired
	public MutanteService(Utilidades utilidades, BuscarDNA buscarDna, IMutanteDao mutanteDao) {
		super();
		this.utilidades = utilidades;
		this.buscarDna = buscarDna;
		this.mutanteDao = mutanteDao;
	}

	public boolean isMutant(String[] dna) {
		LOGGER.info("validar si es un mutante: " + Arrays.toString(dna));
		boolean validaMutante = false;
		
		if(dna == null || dna.length ==0 || !utilidades.validaCadenaDna(dna)) {
			throw new BadRequestException(Constantes.MENSAJE_MAL_REQUEST);
		}
		
		validaMutante = buscarMutante(utilidades.crearMatriz(dna));
		registrarCadenaDna(validaMutante, dna);		
		return validaMutante;
	}	
	
	private boolean buscarMutante(char[][] matrizMutante) {
		LOGGER.info("Buscar si es un mutante");
		int columna = matrizMutante[0].length;
		int fila = matrizMutante.length;
		boolean banderaCadena= false;
		
		for(int i=0; i< fila; i++ ) {
			for(int j=0; j< columna; j++) {
				Character itemPivote = matrizMutante[i][j];
				
				buscarDna.setStrategyBuscarCadena(new StrategyBuscarHorizontal());
				banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
				if(!banderaCadena) {
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarVertical());
					banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
				}else{
					LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
					return banderaCadena;
				}
				
				if(!banderaCadena){
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalDerecha());
					banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
				}else{
					LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
					return banderaCadena;
				}
				
				if(!banderaCadena){
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalIzquierda());
					banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
					
					if(banderaCadena) {
						LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
						return banderaCadena;
					}
				}else{
					LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
					return banderaCadena;
				}				
			}
		}	
		LOGGER.info(Constantes.MENSAJE_SUCCESS_HUMANO);
		return banderaCadena;
	}
	
	private void registrarCadenaDna(boolean ctrMutante, String[] dna) {
		LOGGER.info("Registrar Mutante - Cadena: " + Arrays.toString(dna) + "es mutante: " + ctrMutante);
		Mutante mutante = new Mutante();
		
		mutante.setDna(Arrays.toString(dna));
		mutante.setCtrMutante(ctrMutante?1:0);
		
		mutanteDao.saveAndFlush(mutante);
	}

	public String obtenerEstadisticas() {		
		return mutanteDao.consultaRegistros();
	}
}
