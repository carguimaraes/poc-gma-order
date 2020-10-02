package br.com.gma.poc.shipping.app.service.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.gma.poc.shipping.app.service.EmbarqueService;

@Service
public class EmbarqueServiceImpl implements EmbarqueService {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${queue.order.embarquefinalizado}")
	private String orderQueueEmbarqueFinalizado;

	@RabbitListener(queues = { "${queue.order.paraembarque}" })
	@Override
	public void executar(@Payload String msg) {

		// TODO CRIRAR OBJETO MENSAGEM
		Long id = Long.parseLong(msg.replace("ORDER-ID:", "").trim());

		Queue queue = new Queue(orderQueueEmbarqueFinalizado, true);
		rabbitTemplate.convertAndSend(queue.getName(), "ORDER-ID:" + id);

	}

}
