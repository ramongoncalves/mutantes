package br.com.mutants.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Stats implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer countMutantDna;
	private Integer countHumanDna;
	private BigDecimal ratio;
	
}
