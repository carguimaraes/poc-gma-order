package br.com.gma.poc.order.app.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.gma.poc.order.app.model.CustomerResource;
import br.com.gma.poc.order.app.model.OrderItemResource;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.model.PaymentDetailsResource;
import br.com.gma.poc.order.app.model.ProductResource;
import br.com.gma.poc.order.app.service.impl.MapperOrderImpl;
import br.com.gma.poc.order.domain.entity.Order;

//TODO nao é bem um teste...
//TODO incompleto
public class MapperOrderImplTest {
	
	
	private MapperOrderImpl mapperOrderImpl=new MapperOrderImpl();
	
	 
	
	@Test
	public void toOrder()   {
		
		 
		OrderResource oR=OrderResourceBuilder.build(true);
		
		
		Order o=mapperOrderImpl.toOrder(oR);
			
		OrderResource oR2=  mapperOrderImpl.toOrderResource(o);
		 
		System.out.println("xxxxx");
	}

	@Test
	public void toOrderResource()   {
		
		 
		OrderResource oR=OrderResourceBuilder.build(true);
		Order o=mapperOrderImpl.toOrder(oR);
			
		OrderResource oR2=  mapperOrderImpl.toOrderResource(o);
		 
		System.out.println("xxxxx");
	}
	
	
	
	 
}
