package br.com.gma.poc.order.app.service.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.gma.poc.order.app.service.OrderAprovacaoService;

@Service
public class OrderAprovacaoServiceImpl implements OrderAprovacaoService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${queue.order.paraaprovar}")
	private String orderQueue;
	 

	@Override
	public void enviar(long orderId) {
		
		System.out.println(orderQueue);
		
	 	Queue queue = new  Queue(orderQueue, true);
		
	 	rabbitTemplate.convertAndSend(queue.getName(), "ORDER-ID:" +orderId);
		
	}

}
