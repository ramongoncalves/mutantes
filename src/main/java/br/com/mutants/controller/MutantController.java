package br.com.mutants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mutants.exception.DNAException;
import br.com.mutants.model.Candidato;
import br.com.mutants.repository.CandidatoRepository;
import br.com.mutants.service.MutantService;


@RestController
@RequestMapping("mutant")
public class MutantController {

	private static final char TIPO_HUMAN_CHAR = 'H';

	private static final char TIPO_MUTANT_CHAR = 'M';

	@Autowired
	private MutantService mutantService;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@PostMapping
	public ResponseEntity<String> analizeMutantCandidate(@RequestBody Candidato candidato) {
		
		ResponseEntity<String> responseEntity = null;
		boolean isMutant;	

			try {
			
				isMutant = mutantService.isMutant(candidato.getDna());
				
				if(isMutant) {

					responseEntity = new ResponseEntity<>(HttpStatus.OK);
					candidato.setTipo(TIPO_MUTANT_CHAR);
					
				} else {
					
					responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
					candidato.setTipo(TIPO_HUMAN_CHAR);
				}

				candidatoRepository.save(candidato);
				
			} catch (DNAException e) {
				responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				e.getMessage();
			}
			
		return responseEntity;
		
	}
	
	@GetMapping
	public List<Candidato> listAll() {
		
		return candidatoRepository.findAll();
	}
}
