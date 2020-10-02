package br.com.gma.poc.order.app.service.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.gma.poc.order.app.service.EmbarqueOrderService;

@Service
public class EmbarqueOrderServiceImpl implements EmbarqueOrderService{

 
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${queue.order.paraembarque}")
	private String orderQueue;
	 

	//TODO incompleto crirar objeto mensagem
	@Override
	public void enviar(long orderId) {
		
		Queue queue = new  Queue(orderQueue, true);
		
		rabbitTemplate.convertAndSend(queue.getName(), "ORDER-ID:" +orderId);
		
	}

}
