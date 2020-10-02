package br.com.gma.poc.order.app.exception;

import lombok.Getter;

public class InformacaoNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 3857698137429235523L;
	
	@Getter
	private String[] errors;
	
	public InformacaoNaoEncontradaException(String[] erros) {
		super();
		this.errors = erros;
	}
	
	public InformacaoNaoEncontradaException() {
		super();
		this.errors =new String[] { "Informação não encontrada"};
	}


}
