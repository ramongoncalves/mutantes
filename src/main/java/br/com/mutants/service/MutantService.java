package br.com.mutants.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import br.com.mutants.exception.DNAException;
import br.com.mutants.model.Candidato;

@Service
public class MutantService {

	private char[][] dnaMatrix;
	private static final int MUTACAO_MINIMA = 3;
	private static final int QUANTIDADE_CARACTERS = 4;
	private int quantidadeMutacoes = 0; 
	
	public boolean isMutant(String[] dna) throws DNAException {
		
		dnaMatrix = dnaStringToCharArray(dna);
		boolean isMutant = false;
		
		
		if(dnaMatrix.length < QUANTIDADE_CARACTERS) {
			return false;
		}
		
		// Verifica nos sentidos horizontal e vertical
		for (int indice = 0; indice < dnaMatrix.length; indice++) {
			
			checkHorizontal(dnaMatrix[indice][0], indice );
			checkVertical(dnaMatrix[0][indice], indice );
		}
		
		// Verifica nos sentidos diagonais de cima para baixo
		for (int indice = 0; indice < dnaMatrix.length -1; indice++) {
			
			checkDiagonalTopToBottom(indice, 0);
			checkDiagonalTopToBottom(1, indice);
		}
		
		// Verifica no sentido diagonal de baixo para a direita 
		for (int indice = 0; indice < dnaMatrix.length -1; indice++) {
			
			checkDiagonalBottomToRigth(dnaMatrix.length - 1 ,indice );
		}

		// Verifica no sentido diagonal de baixo para a esquerda
		for (int indice = dnaMatrix.length -1 ; indice >= 0 ; indice--) {
			checkDiagonalBottomToLeft(indice, 0);
		}
		
		Candidato candidato = new Candidato();
		candidato.setDna(dna);

		if(quantidadeMutacoes >= MUTACAO_MINIMA) {

			candidato.setTipo('M');
			isMutant = true;
		} else {
			
			candidato.setTipo('H');
			isMutant = false;
			
		}
		
		
		
		return isMutant;
	}
	
	public void checkHorizontal(char caracter, int indice) {

		int quantidadeEncontada = 1;
		char caracterAnterior = caracter;
		char caracterAtual;

		for (int indiceHorizontal = 1; dnaMatrix.length - indiceHorizontal + quantidadeEncontada >= QUANTIDADE_CARACTERS
					&& indiceHorizontal < dnaMatrix.length; indiceHorizontal++) {

			caracterAtual = dnaMatrix[indice][indiceHorizontal];

			if (caracterAnterior == caracterAtual) {

				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				quantidadeEncontada++;
				
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

				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				quantidadeEncontada++;
				
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
		System.out.println(caracterAnterior);
		char caracterAtual;
		
		boolean isReadable = coluna + increment <= dnaMatrix.length;
		
		while (isReadable) {
			
			caracterAtual = dnaMatrix[linha + increment][coluna + increment];
			System.out.println(caracterAtual);
			
			if(caracterAnterior == caracterAtual) {

				if (quantidadeEncontada == QUANTIDADE_CARACTERS) {

					quantidadeMutacoes++;
					quantidadeEncontada = 0;

				}
				
				quantidadeEncontada++;
				
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
			System.out.println(caracterAtual);
			
			if(caracterAnterior == caracterAtual) {
				
				if(quantidadeEncontrada == QUANTIDADE_CARACTERS) {
					
					quantidadeMutacoes++;
					quantidadeEncontrada = 0;
				}
				
				quantidadeEncontrada++;
				
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
				
				if(quantidadeEncontrada == QUANTIDADE_CARACTERS) {
					
					quantidadeMutacoes++;
					quantidadeEncontrada = 0;
				}
				
				quantidadeEncontrada++;
				
			} else {
				caracterAnterior = caracterAtual;
				quantidadeEncontrada = 1;
			}
			
			increment++;
			
			isReadable = coluna + increment < dnaMatrix.length && linha - increment >= 0;
			
		}
		
	}
	
	
	private char[][] dnaStringToCharArray(String[] dna) throws DNAException{
		
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
	
}
