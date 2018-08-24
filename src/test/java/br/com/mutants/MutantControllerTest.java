package br.com.mutants;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.mutants.controller.MutantController;
import br.com.mutants.exception.DNAException;
import br.com.mutants.repository.CandidatoRepository;
import br.com.mutants.service.MutantService;

@RunWith(SpringRunner.class)
@WebMvcTest(MutantController.class)
public class MutantControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private MutantService service;
	
	@MockBean
	private CandidatoRepository repository;
	
	private String[] dnaMutant = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	private String[] dnaSizeError = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	private String[] dnaHuman = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	private String[] invalidDna = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	
	private String mockMutantBody = "{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGG\",\"AGAAGG\",\"CCCGTA\",\"TCGCTG\"]}";
	
	@Test
	public void dado_dna_mutant_retornar_200_Ok() throws Exception {
	     
		 when(service.isMutant(dnaMutant)).thenReturn(true);
	        this.mvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON).content(mockMutantBody)).andExpect(status().isOk());

	}
	
	@Test
	public void dado_dna_humano_retornar_403_Forbidden() throws Exception {
		
		when(service.isMutant(dnaHuman)).thenReturn(false);
		this.mvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON).content(mockMutantBody)).andExpect(status().isForbidden());
		
	}
	
	@Test
	public void dado_dna_tamanho_errado_retornar_400_Bad_request() throws Exception {
		
		when(service.isMutant(dnaHuman)).thenThrow(new DNAException(""));
		this.mvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON).content(mockMutantBody)).andExpect(status().isBadRequest());
		
	}
	
}
