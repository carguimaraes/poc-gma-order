package testintegracao;
 
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.gma.poc.order.OrderApplication;
import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.service.OrderResourceBuilder;
import br.com.gma.poc.order.app.service.OrderService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = OrderApplication.class)
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	
	//@Test
	//@Rollback(false)
	public void incluir() {
		
		OrderResource oRinc=OrderResourceBuilder.inc_db_build();
	
		try {
			System.out.println("*** INICIO INCLUSAO");
			OrderResource oRret=orderService.incluir(oRinc);
			System.out.println("*** SUCESSO INCLUSÃO ID: "+oRret.getId());
		} catch (DadoInvalidoException e) {
			System.out.println("*** FALHA INCLUSÃO ID");
			for(String item :e.getErrors()) {
				System.out.println("ERRO:===>"+item);
			}
			 
		}
	}
	
	

}
