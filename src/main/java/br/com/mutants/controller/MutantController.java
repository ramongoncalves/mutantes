package br.com.mutants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mutants.exception.DNAException;
import br.com.mutants.model.Candidato;
import br.com.mutants.service.MutantService;


@RestController
@RequestMapping("mutant")
public class MutantController {

	@Autowired
	private MutantService mutantsService;
	
	@PostMapping
	public ResponseEntity<String> analizeMutantCandidate(@RequestBody Candidato dna) {
		
		ResponseEntity<String> responseEntity = null;
		boolean isMutant;	

			try {
			
				isMutant = mutantsService.isMutant(dna.getDna());
				
				if(isMutant) {
					responseEntity = new ResponseEntity<>(HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
				
			} catch (DNAException e) {
				responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				e.getMessage();
			}
			
			
	
		return responseEntity;
		
	}
	
}
