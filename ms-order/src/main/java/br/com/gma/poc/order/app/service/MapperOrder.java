package br.com.gma.poc.order.app.service;

import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.domain.entity.Order;

public interface MapperOrder {
	
	public Order toOrder(OrderResource orderResource);
	
	public OrderResource  toOrderResource(Order order);
	

}
