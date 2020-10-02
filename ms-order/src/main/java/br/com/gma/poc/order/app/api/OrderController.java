package br.com.gma.poc.order.app.api;

 

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.exception.InformacaoNaoEncontradaException;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.service.OrderService;

 
@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> incluir( @RequestBody OrderResource orderResource) throws DadoInvalidoException  {
		
		OrderResource or=orderService.incluir(orderResource);
		
		   //TODO ajustar esta pegando a rota sem zuul
		   URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                   .path("/{id}")
                   .buildAndExpand(or.getId())
                   .toUri();

		
		
		return  ResponseEntity.created(location).build();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public  OrderResource obterOrder(@PathVariable("id") Long id) throws InformacaoNaoEncontradaException {

		 
		return  orderService.obterPorId(id);
	}
	

}
