package testintegracao;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gma.poc.order.OrderApplication;
import br.com.gma.poc.order.app.service.MapperOrder;
import br.com.gma.poc.order.domain.entity.Order;
import br.com.gma.poc.order.domain.repository.CustomerRepository;
import br.com.gma.poc.order.domain.repository.OrderRepository;
import br.com.gma.poc.order.domain.repository.PaymentDetailsRepository;
import br.com.gma.poc.order.domain.repository.ProductRepository;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrderApplication.class)
public class BancoTest {
	
	@Autowired
	private MapperOrder mapperOrder;
	
	@Autowired
	private OrderRepository  orderRepository ;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PaymentDetailsRepository paimentDetailsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	//@Test
	public void insert() {
		
		System.out.println("****INCLUSAO");
		
	 
		 
		
		
		//o=orderRepository.save(o);
		
	}
	
	//@Test
	public void buscar() throws InterruptedException {
		System.out.println("****BUSCAR");
		Order o= orderRepository.findById(2l).get();
		
		System.out.println("****order-nome:"+o.getNome());
		System.out.println("****order-Customer-nome:"+o.getCustomer().getNome());
		System.out.println("****order-Customer-nome:"+o.getPaymentDetails().getNome());
		System.out.println("**********************************************************************");
	//	for(OrderItem item : o.getOrderItems()) {
	//		System.out.println("****order-OrderItem-nome:"+item.getNome());
	//	}
		 
	}
	

	
	

}
