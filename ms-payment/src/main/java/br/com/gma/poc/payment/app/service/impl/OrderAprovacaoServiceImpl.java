package br.com.gma.poc.payment.app.service.impl;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.gma.poc.payment.app.service.OrderAprovacaoService;
import br.com.gma.poc.payment.integracao.ExternalGateway;

@Service
public class OrderAprovacaoServiceImpl implements OrderAprovacaoService {

	@Autowired
	private ExternalGateway externalGateway;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${queue.order.aprovada}")
	private String orderQueueAprovada;

	@Value("${queue.order.rejeitada}")
	private String orderQueueRejeitada;

	@RabbitListener(queues = { "${queue.order.paraaprovar}" })
	@Override
	public void executar(@Payload String msg) {

		// TODO CRIRAR OBJETO MENSAGEM
		Long id = Long.parseLong(msg.replace("ORDER-ID:", "").trim());

		if (externalGateway.isAprovado(id)) {
			System.out.println("APROVADA:"+id);
			Queue queue = new Queue(orderQueueAprovada, true);
			rabbitTemplate.convertAndSend(queue.getName(), "ORDER-ID:" + id);
		} else {
			System.out.println("REJEITADA:"+id);
			Queue queue = new Queue(orderQueueRejeitada, true);
			rabbitTemplate.convertAndSend(queue.getName(), "ORDER-ID:" + id);
		}
	}

}
