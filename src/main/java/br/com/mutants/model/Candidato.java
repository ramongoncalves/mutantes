package br.com.mutants.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Candidato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String[] dna;
	private char tipo;
	
}
