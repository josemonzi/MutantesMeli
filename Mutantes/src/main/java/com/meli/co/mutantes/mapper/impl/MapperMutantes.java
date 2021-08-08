package com.meli.co.mutantes.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meli.co.mutantes.dto.EstadisticaDTO;
import com.meli.co.mutantes.exception.ModeloNotFoundException;
import com.meli.co.mutantes.mapper.IMapperMutantes;
import com.meli.co.mutantes.util.Constantes;

public class MapperMutantes implements IMapperMutantes{
	private static final Logger LOGGER = LoggerFactory.getLogger(MapperMutantes.class);	
	
	@Override
	public EstadisticaDTO obtenerEstadisticas(String consultaRegistros) {
		LOGGER.info("Obtener estadisticas de la bd: " + consultaRegistros);
		
		if(consultaRegistros == null || consultaRegistros.isEmpty())
			throw new ModeloNotFoundException(Constantes.MENSAJE_NULO);
		String[] elementosConsulta =consultaRegistros.split(",");
		
		EstadisticaDTO estadistica = new EstadisticaDTO(Integer.parseInt(elementosConsulta[0]),
				Integer.parseInt(elementosConsulta[1]), Double.parseDouble(elementosConsulta[2]));
		return estadistica;
	}

}
