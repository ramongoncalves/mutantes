package br.com.mutants.service;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mutants.exception.DNAException;
import br.com.mutants.model.Stats;
import br.com.mutants.repository.CandidatoRepository;

@Service
public class MutantService {

	private static final BigDecimal ZERO_BIGDECIMAL = new BigDecimal(0);

	@Autowired
	private CandidatoRepository candidatoRepository;
	
	private char[][] dnaMatrix;
	private static final int MUTACAO_MINIMA = 3;
	private static final int QUANTIDADE_CARACTERS = 4;
	private int quantidadeMutacoes; 
	
	public boolean isMutant(String[] dna) throws DNAException {

		quantidadeMutacoes = 0;

		dnaMatrix = dnaStringToCharArray(dna);

		if (dnaMatrix.length < QUANTIDADE_CARACTERS) {
			return false;
		}

		// Verifica nos sentidos horizontal e vertical
		for (int indice = 0; indice < dnaMatrix.length; indice++) {

			checkHorizontal(dnaMatrix[indice][0], indice);
			checkVertical(dnaMatrix[0][indice], indice);
		}

		// Verifica nos sentidos diagonais de cima para baixo
		for (int indice = 0; indice < dnaMatrix.length - 1; indice++) {

			checkDiagonalTopToBottom(indice, 0);
			checkDiagonalTopToBottom(1, indice);
		}

		// Verifica no sentido diagonal de baixo para a direita
		for (int indice = 0; indice < dnaMatrix.length - 1; indice++) {

			checkDiagonalBottomToRigth(dnaMatrix.length - 1, indice);
		}

		// Verifica no sentido diagonal de baixo para a esquerda
		for (int indice = dnaMatrix.length - 1; indice >= 0; indice--) {
			checkDiagonalBottomToLeft(indice, 0);
		}

		return quantidadeMutacoes >= MUTACAO_MINIMA;

	}
	
	public void checkHorizontal(char caracter, int indice) {

		int quantidadeEncontada = 1;
		char caracterAnterior = caracter;
		char caracterAtual;

		for (int indiceHorizontal = 1; dnaMatrix.length - indiceHorizontal + quantidadeEncontada >= QUANTIDADE_CARACTERS
					&& indiceHorizontal < dnaMatrix.length; indiceHorizontal++) {

			caracterAtual = dnaMatrix[indice][indiceHorizontal];

			if (caracterAnterior == caracterAtual) {

				quantidadeEncontada++;
				
				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontada = 1;
			}
		}
	}

	public void checkVertical(char caracter, int indice) {

		int quantidadeEncontada = 1;
		char caracterAnterior = caracter;
		char caracterAtual;

		for (int indiceVertical = 1; dnaMatrix.length - indiceVertical + quantidadeEncontada >= QUANTIDADE_CARACTERS
				&& indiceVertical < dnaMatrix.length; indiceVertical++) {

			caracterAtual = dnaMatrix[indiceVertical][indice];

			if (caracterAnterior == caracterAtual) {

				quantidadeEncontada++;
				
				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontada = 1;
			}
		}
	}
	
	private void checkDiagonalTopToBottom(int linha, int coluna) {
		
		int increment = 1;
		
		int quantidadeEncontada = 1;
		
		char caracterAnterior = dnaMatrix[linha][coluna];
		
		char caracterAtual;
		
		boolean isReadable = coluna + increment <= dnaMatrix.length;
		
		while (isReadable) {
			
			caracterAtual = dnaMatrix[linha + increment][coluna + increment];
			
			if(caracterAnterior == caracterAtual) {

				quantidadeEncontada++;
				
				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontada = 1;
			}
			
			increment++;
			
			isReadable = coluna + increment < dnaMatrix.length && linha + increment < dnaMatrix.length;
			
		}
		
	}
	
	
	private void checkDiagonalBottomToRigth(int linha, int coluna) {
		
		int increment = 1;
		
		int quantidadeEncontrada = 1;	
												
		char caracterAnterior = dnaMatrix[linha][coluna];
		
		char caracterAtual;
		
		boolean isReadable = coluna + increment <= dnaMatrix.length;
		
		while (isReadable) {
			
			caracterAtual = dnaMatrix[linha - increment][coluna + increment];
		
			if(caracterAnterior == caracterAtual) {
				
				quantidadeEncontrada++;
				
				if(quantidadeEncontrada == QUANTIDADE_CARACTERS) {
					
					quantidadeMutacoes++;
					quantidadeEncontrada = 0;
				}
				
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontrada = 1;
			}
			
			increment++;
			
			isReadable = coluna + increment < dnaMatrix.length && linha - increment >= 0;
			
		}
		
	}
	
	private void checkDiagonalBottomToLeft(int linha, int coluna) {
		
		int increment = 1;
		
		int quantidadeEncontrada = 1;	
		
		char caracterAnterior = dnaMatrix[linha][coluna];

		char caracterAtual;
		
		boolean isReadable = coluna + increment < dnaMatrix.length && linha - increment >= 0;
		
		while (isReadable) {
			
			caracterAtual = dnaMatrix[linha - increment][coluna + increment];
			
			if(caracterAnterior == caracterAtual) {
				
				quantidadeEncontrada++;
				
				if(quantidadeEncontrada == QUANTIDADE_CARACTERS) {
					
					quantidadeMutacoes++;
					quantidadeEncontrada = 0;
				}
				
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontrada = 1;
			}
			
			increment++;
			
			isReadable = coluna + increment < dnaMatrix.length && linha - increment >= 0;
			
		}
		
	}
	
	
	private char[][] dnaStringToCharArray(String[] dna) throws DNAException {
		
		Integer dnaLength = dna.length;
		Pattern pattern = Pattern.compile("[acgt]+", Pattern.CASE_INSENSITIVE);
		
		dnaMatrix = new char[dnaLength][dnaLength];
		
		for (int position = 0; position < dnaLength ; position++) {
			
			if( !pattern.matcher(dna[position]).matches() || dna[position].length() != dnaLength ) {
				throw new DNAException(String.format("As sequências de caracters devem conter %d caracters das letras A, C, G e T.", dnaLength))   ;
			} else {
				dnaMatrix[position] = dna[position].toUpperCase().toCharArray();
			}
		}
			
		return dnaMatrix;
	}

	public Stats getStats() {
		
		Stats stats = new Stats();
		
		Long countHuman = candidatoRepository.countHumans();
		Long countMutants = candidatoRepository.countMutants();
		
		stats.setCountHumanDna(countHuman);
		stats.setCountMutantDna(countMutants);
		
		BigDecimal ratio = calculaRatio(countHuman, countMutants);
		
		stats.setRatio(ratio);
		
		return stats;
	}

	private BigDecimal calculaRatio(Long countMutants, Long countHuman) {

		BigDecimal mutants = new BigDecimal(countMutants);	
		BigDecimal humans = new BigDecimal(countHuman);
		
		if(humans.compareTo(ZERO_BIGDECIMAL) > 0) {
			return mutants.divide(humans);
		}
		
		return ZERO_BIGDECIMAL;
	}

	
}
