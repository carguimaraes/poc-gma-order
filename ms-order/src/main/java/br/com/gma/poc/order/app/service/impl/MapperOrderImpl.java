package br.com.gma.poc.order.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.service.MapperOrder;
import br.com.gma.poc.order.domain.entity.Order;

@Component
public class MapperOrderImpl implements MapperOrder {
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Order toOrder(OrderResource orderResource) {
		Order o = modelMapper.map(orderResource, Order.class);

		return o;
		 
	}

	@Override
	public OrderResource toOrderResource(Order order) {
		OrderResource oR = modelMapper.map(order, OrderResource.class);

		return oR;
	}

}
