package com.meli.co.mutantes.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
		String[] dna = new String[] {"ATGCGA","CGGTGC","TTATGT","AGAATA","CCCCTA","TCACTT"};
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
		String[] dna = new String[] {"ATG","AAG","ATA", "AGA"};
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
		String[] dna = new String[] {"ATGCGA","CGTGGC","TTCTGT","ACAATG","CGCCTA","TCACTC"};
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
		String[] dna = new String[] {"ATGCGA","CGGCGC","TTCTGT","ACAATA","CGCCTA","TCACTG"};
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
		when(mutanteDao.consultaRegistros()).thenReturn("2,5,0.4000");
		
		
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
	public void cuandoConsultaEstadisticasEntoncesNORetornaDnaHumanoNODnaMutanteNOProporcionDevuelve200() throws Exception {			
		when(mutanteDao.consultaRegistros()).thenReturn("0,0,NULL");
		
		
		mvcMock.perform(
				get("/stats").contentType(MediaType.APPLICATION_JSON))						
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void cuandoConsultaEstadisticasEntoncesRetornaVacioNORetornaDnaHumanoNODnaMutanteNOProporcionDevuelve404() throws Exception {			
		when(mutanteDao.consultaRegistros()).thenReturn("");
		
		
		mvcMock.perform(
				get("/stats").contentType(MediaType.APPLICATION_JSON))						
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
