package com.meli.co.mutantes.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.dto.MatrizDTO;
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
		MatrizDTO cantidadMutante = new MatrizDTO(0);
		int columna = matrizMutante[0].length;
		int fila = matrizMutante.length;
		Character itemPivote = null;
		char[][] matrizTmp = matrizMutante;
		
		for(int i=0; i< fila; i++ ) {
			for(int j=0; j< columna; j++) {
				itemPivote = matrizTmp[i][j];	
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarHorizontal());
					matrizTmp = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante, cantidadMutante);				
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarVertical());
					matrizTmp = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante, cantidadMutante);
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalDerecha());				
					matrizTmp = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante, cantidadMutante);
					buscarDna.setStrategyBuscarCadena(new StrategyBuscarDiagonalIzquierda());
					matrizTmp = buscarDna.ejecutarStrategy(i, j, itemPivote, matrizMutante, cantidadMutante);
				if(validarBusquedaMutante(cantidadMutante.getContadorMutante())){
					return true;					
				};	
			}
		}	
		LOGGER.info(Constantes.MENSAJE_SUCCESS_HUMANO);
		return false;
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
	
	private boolean validarBusquedaMutante(int cantidadMutante) {
		return cantidadMutante>=2?true:false;
	}
}
