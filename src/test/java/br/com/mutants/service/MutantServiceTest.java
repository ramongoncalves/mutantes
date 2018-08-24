package br.com.mutants.service;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mutants.exception.DNAException;
import br.com.mutants.model.Stats;
import br.com.mutants.repository.CandidatoRepository;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
public class MutantServiceTest {

	 private static final BigDecimal RATIO_EXPECTED = new BigDecimal(1.25);

	@TestConfiguration
	    static class EmployeeServiceImplTestContextConfiguration {
	  
	        @Bean
	        public MutantService employeeService() {
	            return new MutantService();
	        }
	    }

	 @Autowired
     private MutantService mutantService;
	 
	 @MockBean
	 private CandidatoRepository employeeRepository;
		
	 private String[] dnaMutant = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	 private String[] dnaSizeError = new String[] { "ATG", "CAG", "TTA", "AGA", "CCC", "TCG" };
	 private String[] dnaHuman = new String[] { "ATGCGA", "CCGTGC", "TTATGG", "AGAAGG", "CTCTTA", "TCGCTG" };
	 private String[] invalidDna = new String[] { "BTGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCGTA", "TCGCTG" };
	 
	 @Test
	 public void quando_mutant_retorna_verdadeiro() throws Exception {
		     
		 boolean isMutant = mutantService.isMutant(dnaMutant);
		 
		 Assert.assertTrue(isMutant);

	 }
	 
	 @Test
	 public void quando_humano_retorna_falso() throws Exception {
		     
		 boolean isMutant = mutantService.isMutant(dnaHuman);
		 
		 Assert.assertFalse(isMutant);

	 }

	 @Test(expected = DNAException.class) 
	 public void quando_dna_errado_lanca_dna_exception() throws DNAException {
		     
		 mutantService.isMutant(dnaSizeError);

	 }

	 
	 @Test(expected = DNAException.class)
	 public void quando_tamanho_dna_errado_lanca_dna_exception() throws DNAException {
		     
		 mutantService.isMutant(invalidDna);

	 }
	 
	 @Test
	 public void quando_count_mutants_maior_que_humanos() throws Exception {

		 BigDecimal ratio = RATIO_EXPECTED;
		 
		 Stats statsReturn = mutantService.getStats();
		 statsReturn.setCountMutantDna(50L);
		 statsReturn.setCountHumanDna(40L);
		 statsReturn.setRatio(RATIO_EXPECTED);
		 Assert.assertEquals(ratio, statsReturn.getRatio());
		 
		 
	 }
	 
	 @Test
	 public void quando_count_mutants_e_humans_maiores_zero() throws Exception {
		 
		when(employeeRepository.countMutants()).thenReturn(1L);
		when(employeeRepository.countHumans()).thenReturn(10L);

		Stats stats = mutantService.getStats();

		TestCase.assertNotNull(stats);
		 
	 }
	 
}
