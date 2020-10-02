package br.com.gma.poc.order.infra.configuration;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueConfig {

	@Value("${queue.order.paraaprovar}")
	private String orderQueueParaAprovar;
	
	@Value("${queue.order.aprovada}")
	private String orderQueueAprovada;

	@Value("${queue.order.rejeitada}")
	private String orderQueueRejeitada;
	
	@Value("${queue.order.paraembarque}")
	private String orderQueueParaEmbarque;
	
	@Value("${queue.order.embarquefinalizado}")
	private String orderQueueEmbarqueFinalizado;
	
	 	
	private AmqpAdmin amqpAdmin;

	public QueueConfig(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	@PostConstruct
	public void createQueues() {
		amqpAdmin.declareQueue(new Queue(orderQueueParaAprovar, true));
		amqpAdmin.declareQueue(new Queue(orderQueueAprovada, true));
		
		amqpAdmin.declareQueue(new Queue(orderQueueRejeitada, true));
		amqpAdmin.declareQueue(new Queue(orderQueueParaEmbarque, true));
		
		amqpAdmin.declareQueue(new Queue(orderQueueEmbarqueFinalizado, true));
	}
}
