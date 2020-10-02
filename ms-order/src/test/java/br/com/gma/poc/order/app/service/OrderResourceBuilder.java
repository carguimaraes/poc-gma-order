package br.com.gma.poc.order.app.service;

import java.util.ArrayList;
import java.util.List;

import br.com.gma.poc.order.app.model.CustomerResource;
import br.com.gma.poc.order.app.model.OrderItemResource;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.model.PaymentDetailsResource;
import br.com.gma.poc.order.app.model.ProductResource;

public class OrderResourceBuilder {
	
	//TODO nao melhorar Builder aplicar padrao Builder para testes criação de objetos
	public static  OrderResource build(boolean isValid) {
		List<OrderItemResource> orderItems= new ArrayList<>();
		
		//CustomerResource customer=(isValid)? new CustomerResource(219670430L, "CLIENTE-01"): new CustomerResource();
		CustomerResource customer=(isValid)? new CustomerResource(219670430L, null): new CustomerResource();
		
		//PaymentDetailsResource paymentDetails= (isValid)? new PaymentDetailsResource(30L, "PAYMENT-D-01"): new PaymentDetailsResource() ;
		PaymentDetailsResource paymentDetails= (isValid)? new PaymentDetailsResource(30L, null): new PaymentDetailsResource() ;
		
		//ProductResource p1= (isValid)? new ProductResource(04L,"PRODUTO-01"):new ProductResource();
		//ProductResource p2= (isValid)? new ProductResource(05L,"PRODUTO-02"):new ProductResource();
		ProductResource p1= (isValid)? new ProductResource(04L,null):new ProductResource();
		ProductResource p2= (isValid)? new ProductResource(05L,null):new ProductResource();
		OrderItemResource orderItem1= (isValid)? new OrderItemResource(20L,"ITEM-01"):new OrderItemResource() ;
		OrderItemResource orderItem2= (isValid)? new OrderItemResource(21L,"ITEM-02"):new OrderItemResource() ;
		
		orderItem1.setProduct(p1);
		orderItem2.setProduct(p2);
		orderItems.add(orderItem1);
		orderItems.add(orderItem2);
	
		
		OrderResource orderResource=  new OrderResource();
		orderResource.setId(0L);
		orderResource.setCustomer(customer);		
		orderResource.setPaymentDetails(paymentDetails);
		orderResource.setOrderItems(orderItems);
		if(isValid) {
			orderResource.setNome("ORDER-NOME");
			orderResource.setObservacao("ORDER-OBS");
		}
		
		return orderResource;
	}
	
	public static  OrderResource inc_db_build() {
		List<OrderItemResource> orderItems= new ArrayList<>();
		
		CustomerResource customer= new CustomerResource(2L, "CLIENTE-01");
		
		PaymentDetailsResource paymentDetails=  new PaymentDetailsResource(3L, "PAYMENT-D-01") ;
		
		ProductResource p1=  new ProductResource(02L,"PRODUTO-01");
		ProductResource p2=  new ProductResource(03L,"PRODUTO-02");
		OrderItemResource orderItem1= new OrderItemResource(0L,"ITEM-01") ;
		OrderItemResource orderItem2=  new OrderItemResource(0L,"ITEM-02") ;
		
		orderItem1.setProduct(p1);
		orderItem2.setProduct(p2);
		orderItems.add(orderItem1);
		orderItems.add(orderItem2);
	
		
		OrderResource orderResource=  new OrderResource();
		orderResource.setId(0L);
		orderResource.setCustomer(customer);		
		orderResource.setPaymentDetails(paymentDetails);
		orderResource.setOrderItems(orderItems);
		orderResource.setNome("ORDER-NOME");
		orderResource.setObservacao("ORDER-OBS");
		 
		
		return orderResource;
	}

}
