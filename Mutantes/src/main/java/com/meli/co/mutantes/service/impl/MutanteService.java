package com.meli.co.mutantes.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.entities.Mutante;
import com.meli.co.mutantes.exception.BadRequestException;
import com.meli.co.mutantes.exception.ForbiddenException;
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
		
		if( null == dna || 0 == dna.length  || !utilidades.validaCadenaDna(dna)) {
			throw new BadRequestException(Constantes.MENSAJE_MAL_REQUEST);
		}
		
		validaMutante = buscarMutante(utilidades.crearMatriz(dna));
		registrarCadenaDna(validaMutante, dna);	
		
		if(!validaMutante)
			throw new ForbiddenException(HttpStatus.FORBIDDEN);	
			
		return validaMutante;
	}	
	
	private boolean buscarMutante(char[][] matrizMutante) {
		LOGGER.info("Buscar si es un mutante");
		int columna = matrizMutante[0].length;
		int fila = matrizMutante.length;
		int contadorMutante = 0; 
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
					contadorMutante++;					
					if(contadorMutante >=2)
						return banderaCadena;
					else
						banderaCadena= false;
				}
				
				if(!banderaCadena){
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalDerecha());
					banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
				}else{
					LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
					contadorMutante++;
					if(contadorMutante >=2)
						return banderaCadena;
					else
						banderaCadena= false;
				}
				
				if(!banderaCadena){
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalIzquierda());
					banderaCadena = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante);
					
					if(banderaCadena) {
						LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
						contadorMutante++;						
						if(contadorMutante >=2)
							return banderaCadena;
						else
							banderaCadena= false;
					}
				}else{
					LOGGER.info(Constantes.MENSAJE_SUCCESS_MUTANTE);
					contadorMutante++;					
					if(contadorMutante >=2)
						return banderaCadena;
					else
						banderaCadena= false;
				}				
			}
		}	
		LOGGER.info(Constantes.MENSAJE_SUCCESS_HUMANO);
		return banderaCadena;
	}
	
	private void registrarCadenaDna(boolean ctrMutante, String[] dna) {
		LOGGER.info("Registrar Mutante - Cadena: " + Arrays.toString(dna) + " es mutante: " + ctrMutante);
		Mutante mutante = new Mutante();
		String cadenaDna = utilidades.convertirDnaSHA256(Arrays.toString(dna)); 
		
		Mutante mutanteEntetie = mutanteDao.findByDna(cadenaDna);
		
		if(null == mutanteEntetie) {
			LOGGER.info("Se registrar Cadena DNA");
			mutante.setDna(cadenaDna);
			mutante.setCtrMutante(ctrMutante?1:0);
			mutanteDao.saveAndFlush(mutante);
		}else {
			LOGGER.info("No se registrar Cadena DNA");
		}		
	}

	public List<Mutante> obtenerEstadisticas() {		
		return  mutanteDao.findAll();
	}
}
