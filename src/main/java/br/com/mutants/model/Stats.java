package br.com.mutants.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"count_mutant_dna", "count_human_dna", "ratio"})
public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("count_mutant_dna")
	private Long countMutantDna;
	
	@JsonProperty("count_human_dna")
	private Long countHumanDna;
	
	private BigDecimal ratio;
	
}
