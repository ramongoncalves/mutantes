package br.com.mutants.exception;

public class DNAException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public DNAException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
