package br.com.mutants.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mutants.model.Candidato;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CandidatoRepositoryTest {

	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void retorna_quantidade_mutants() {

		Long countMutantsEpected = 1L;
	    Candidato candidato = new Candidato();
	    candidato.setDna(new String[] {"1"});
		candidato.setTipo('M');
		candidatoRepository.save(candidato);
	    entityManager.flush();
	 
	    Long countMutantsFound = candidatoRepository.countMutants();
	 
	    assertTrue(countMutantsFound.equals(countMutantsEpected));
	}

	@Test
	public void retorna_quantidade_humanos() {

		Long countHumansEpected = 1L;
	    Candidato candidato = new Candidato();
		candidato.setDna(new String[] {"1"});
		candidato.setTipo('H');
		candidatoRepository.save(candidato);
	    entityManager.flush();
	 
	    Long countHumansFound = candidatoRepository.countHumans();
	 
	    assertTrue(countHumansFound.equals(countHumansEpected));
	}
	
}
