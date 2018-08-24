package br.com.mutants.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import br.com.mutants.controller.StatsController;
import br.com.mutants.model.Stats;
import br.com.mutants.service.MutantService;

@RunWith(SpringRunner.class)
@WebMvcTest(StatsController.class)
public class StatsControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private MutantService service;

	private String mockResponse = "{\"count_mutant_dna\":0,\"count_human_dna\":0,\"ratio\":0}";
	
	@Test
	public void recuperar_stats_retornar_200_Ok() throws Exception {
	     
		Stats stat = new Stats();
		stat.setCountHumanDna(0L);
		stat.setCountMutantDna(0L);
		stat.setRatio(BigDecimal.ZERO);
		
		 when(service.getStats()).thenReturn(stat);
	     MvcResult returnObj = this.mvc.perform(get("/stats")).andExpect(status().isOk()).andReturn();

	 	 String content = returnObj.getResponse().getContentAsString();
		 Assert.isTrue(content.equals(mockResponse), "Objeto diferente retornado");
	}
	
	
}
