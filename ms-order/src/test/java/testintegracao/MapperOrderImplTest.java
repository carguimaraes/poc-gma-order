package testintegracao;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gma.poc.order.OrderApplication;
import br.com.gma.poc.order.app.model.CustomerResource;
import br.com.gma.poc.order.app.model.OrderItemResource;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.model.PaymentDetailsResource;
import br.com.gma.poc.order.app.model.ProductResource;
import br.com.gma.poc.order.app.service.MapperOrder;
import br.com.gma.poc.order.app.service.OrderResourceBuilder;
import br.com.gma.poc.order.app.service.impl.MapperOrderImpl;
import br.com.gma.poc.order.domain.entity.Order;

//TODO nao é bem um teste...
//TODO incompleto

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrderApplication.class)
public class MapperOrderImplTest {
	
	@Autowired
	private MapperOrder mapperOrder;
	
	 
	
	//@Test
	public void toOrder()   {
		
		
		 
		OrderResource oR=OrderResourceBuilder.build(true);
		
		
		Order o=mapperOrder.toOrder(oR);
		
		//int tot=o.getOrderItems().size();
		
			
		OrderResource oR2=  mapperOrder.toOrderResource(o);
		System.out.println("xxxxx"); 
		
	}

	//@Test
	public void toOrderResource()   {
		
		 
		OrderResource oR=OrderResourceBuilder.build(true);
		Order o=mapperOrder.toOrder(oR);
			
		OrderResource oR2=  mapperOrder.toOrderResource(o);
		 
		System.out.println("xxxxx");
	}
	
	
	
	 
}
