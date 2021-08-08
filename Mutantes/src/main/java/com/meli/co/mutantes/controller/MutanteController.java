package com.meli.co.mutantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.co.mutantes.dto.EstadisticaDTO;
import com.meli.co.mutantes.dto.MutanteDnaDTO;
import com.meli.co.mutantes.mapper.IMapperMutantes;
import com.meli.co.mutantes.service.IMutanteService;
import com.meli.co.mutantes.util.Constantes;

@RestController
public class MutanteController {
	
	private IMutanteService mutanteService;
	private IMapperMutantes mapperMutantes;
	
	@Autowired
	public MutanteController(IMutanteService mutanteService, IMapperMutantes mapperMutantes) {
		super();
		this.mutanteService = mutanteService;
		this.mapperMutantes = mapperMutantes;
	}

	@CrossOrigin
	@PostMapping(value = Constantes.MUTANTE_CONTROLLER, produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> isMutant(@RequestBody MutanteDnaDTO dna) {		
		if(!mutanteService.isMutant(dna.getDna())) 
			return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping(value = Constantes.ESTADISTICA_CONTROLLER, produces = "application/json")
	public EstadisticaDTO obtenerEstadisticas() {		
		return mapperMutantes.obtenerEstadisticas(mutanteService.obtenerEstadisticas());
	}
	
}
