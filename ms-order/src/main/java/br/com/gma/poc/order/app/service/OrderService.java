package br.com.gma.poc.order.app.service;

import java.util.List;

import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.exception.InformacaoNaoEncontradaException;
import br.com.gma.poc.order.app.model.OrderResource;

public interface OrderService {

 
	
	public OrderResource incluir(OrderResource escritorio) throws  DadoInvalidoException;

	public OrderResource obterPorId(Long id) throws InformacaoNaoEncontradaException;

	 
	 
}
