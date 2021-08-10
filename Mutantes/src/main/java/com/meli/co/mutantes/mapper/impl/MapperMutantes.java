package com.meli.co.mutantes.mapper.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meli.co.mutantes.dto.EstadisticaDTO;
import com.meli.co.mutantes.entities.Mutante;
import com.meli.co.mutantes.exception.ModeloNotFoundException;
import com.meli.co.mutantes.mapper.IMapperMutantes;
import com.meli.co.mutantes.util.Constantes;

public class MapperMutantes implements IMapperMutantes{
	private static final Logger LOGGER = LoggerFactory.getLogger(MapperMutantes.class);	
	
	@Override
	public EstadisticaDTO obtenerEstadisticas( List<Mutante> consultaRegistros) {
		LOGGER.info("Obtener estadisticas de la bd");
		
		if( null == consultaRegistros|| consultaRegistros.isEmpty())
			throw new ModeloNotFoundException(Constantes.MENSAJE_NULO);
		
		final int dnaMutante = (int) consultaRegistros.stream().filter(x -> x.getCtrMutante() == 1).count();
		final int dnaHumano = (int) consultaRegistros.stream().filter(x -> x.getCtrMutante() == 0).count();
		final double proporcion = (double) dnaMutante/dnaHumano;
				
		return new EstadisticaDTO(dnaMutante, dnaHumano, proporcion);
	}

}
