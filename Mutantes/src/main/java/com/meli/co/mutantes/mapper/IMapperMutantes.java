package com.meli.co.mutantes.mapper;

import java.util.List;

import com.meli.co.mutantes.dto.EstadisticaDTO;
import com.meli.co.mutantes.entities.Mutante;

public interface IMapperMutantes {
	EstadisticaDTO obtenerEstadisticas(List<Mutante> consultaRegistros);
}
