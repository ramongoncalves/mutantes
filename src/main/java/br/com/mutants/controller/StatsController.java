package br.com.mutants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mutants.model.Stats;
import br.com.mutants.service.MutantService;

@RestController
@RequestMapping("stats")
public class StatsController {

	@Autowired
	private MutantService mutantService;
	
	@GetMapping
	public Stats getStats() {
		
		return mutantService.getStats();
	}
	
}
