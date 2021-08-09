package com.meli.co.mutantes.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.co.mutantes.dao.IMutanteDao;
import com.meli.co.mutantes.dto.MutanteDnaDTO;
import com.meli.co.mutantes.entities.Mutante;
import com.meli.co.mutantes.exception.ResponseExceptionHandler;
import com.meli.co.mutantes.mapper.IMapperMutantes;
import com.meli.co.mutantes.mapper.impl.MapperMutantes;
import com.meli.co.mutantes.service.IMutanteService;
import com.meli.co.mutantes.service.impl.BuscarDNA;
import com.meli.co.mutantes.service.impl.MutanteService;
import com.meli.co.mutantes.util.Utilidades;

@RunWith(SpringRunner.class)
@WebMvcTest(MutanteController.class)
public class MutantesControllerTest {
	private MockMvc mvcMock;
	
	@MockBean
	private IMapperMutantes mapperMutantes;
	@MockBean
	private IMutanteService mutanteService;
	private Utilidades utilidades;
	private BuscarDNA buscarDna;
	private IMutanteDao mutanteDao;
	
	
	@Before
	public void setUp() {
		mapperMutantes = new MapperMutantes();
		buscarDna = new BuscarDNA();
		mutanteDao = mock(IMutanteDao.class);
		utilidades = new Utilidades();
		
		mutanteService = new MutanteService(utilidades, buscarDna, mutanteDao);		
		mvcMock = MockMvcBuilders
				.standaloneSetup(new MutanteController(mutanteService, mapperMutantes))
				.setControllerAdvice(new ResponseExceptionHandler())
				.build();
	}

	@Test
	public void cuandoLlegaCadenaMutanteValidaHorizontalEntoncesValidaSiEsMutanteRetornado200() throws Exception {		
		String[] dna = new String[] {"ATGCGA","CGGTGC","TTTTGT","AGAATA","CCCCGA","TCACTT"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaHorizontalX2EntoncesValidaSiEsMutanteRetornado200() throws Exception {		
		String[] dna = new String[] {"ATGCGA","CAGTGC","TCTTTT","AGTATA","CCCCTA","TCACTT"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaVerticalEntoncesValidaSiEsMutanteRetornado200() throws Exception {		
		String[] dna = new String[] {"ATGCGA","CGGTGC","TTATGT","AGTTTA","CTCTTA","TCACTT"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaDiagonalDerechaEntoncesValidaSiEsMutanteRetornado200() throws Exception {		
		String[] dna = new String[] {"ATGCGA","CGGAGC","TATTGT","AGATTA","CCCCTA","TCACTT"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void cuandoLlegaCadenaMutanteValidaDiagonalIzquierdaEntoncesValidaSiEsMutanteRetornado200() throws Exception {		
		String[] dna = new String[] {"ATGCGA","CGGAGC","TATTCT","AGACTA","TCCCTA","TCTTTT"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk());
	}	
	
	@Test
	public void cuandoLlegaCadenaMutanteNoValidaEntoncesNoValidaSiEsMutanteRetornado400() throws Exception {		
		String[] dna = new String[] {};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteNoValidaNullEntoncesNoValidaSiEsMutanteRetornado400() throws Exception {		
		String[] dna = new String[] {""};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void cuandoLlegaCadenaMutanteNoValidaEntoncesValidaSiEsMutanteRetornado403() throws Exception {		
		String[] dna = new String[] {"ATGCCA","CGGTGC","TTATGT","AGAAGG","CTCCTA","TCACTA"};
		final ObjectMapper mapper = new ObjectMapper();
		MutanteDnaDTO mutanteDnaDto = new MutanteDnaDTO();
		mutanteDnaDto.setDna(dna);
		final String jsonRequest = mapper.writeValueAsString(mutanteDnaDto);
		
		
		mvcMock.perform(
				post("/mutant")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isForbidden());
	}
	
	@Test
	public void cuandoConsultaEstadisticasEntoncesRetornaDnaHumanoDnaMutanteProporcion200() throws Exception {	
		List<Mutante> mutante = new ArrayList<Mutante>();
		Mutante m = new Mutante();		
		m.setIdMutante(1);
		m.setDna("777bcb9d92b262d4847f8c87caff1c66dcd1c093b76409982c70a30e5fcaa149");
		m.setCtrMutante(1);
		mutante.add(m);
		
		m = new Mutante();		
		m.setIdMutante(1);
		m.setDna("e8f4281a5012eada6940aaa2d1e392acfbf0913d84c37da3af8949492b5820dd");
		m.setCtrMutante(0);
		mutante.add(m);
		
		m = new Mutante();		
		m.setIdMutante(1);
		m.setDna("964AAC7109FA552E199601C8F6357121DA859771DA83BA84B3E3B5823B10373C");
		m.setCtrMutante(1);
		mutante.add(m);	
		
		when(mutanteDao.findAll()).thenReturn(mutante);
		
		
		mvcMock.perform(
				get("/stats").contentType(MediaType.APPLICATION_JSON))						
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void cuandoConsultaEstadisticasEntoncesNORetornaDnaHumanoNODnaMutanteNOProporcionDevuelve404() throws Exception {
		mvcMock.perform(
				get("/stats"))						
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void cuandoConsultaEstadisticasEntoncesObjetoVacioNORetornaDnaHumanoNODnaMutanteNOProporcionDevuelve404() throws Exception {			
		List<Mutante> mutante = new ArrayList<Mutante>();		
		
		when(mutanteDao.findAll()).thenReturn(mutante);		
		
		mvcMock.perform(
				get("/stats").contentType(MediaType.APPLICATION_JSON))						
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void cuandoConsultaEstadisticasEntoncesRetornaVacioNORetornaDnaHumanoNODnaMutanteNOProporcionDevuelve404() throws Exception {			
		when(mutanteDao.findAll()).thenReturn(null);
		
		
		mvcMock.perform(
				get("/stats").contentType(MediaType.APPLICATION_JSON))						
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
