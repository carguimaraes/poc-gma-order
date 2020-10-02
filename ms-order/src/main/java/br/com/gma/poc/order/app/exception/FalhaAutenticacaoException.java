package br.com.gma.poc.order.app.exception;

import lombok.Getter;

 
public class FalhaAutenticacaoException extends Exception {

	private static final long serialVersionUID = 3857698137429235523L;
	
	@Getter
	private String[] errors;
	
	public FalhaAutenticacaoException(String[] erros) {
		super();
		this.errors = erros;
	}

}
