package br.com.gma.poc.order.app.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gma.poc.order.app.service.ProcessarOrderRejeitadaService;
import br.com.gma.poc.order.domain.entity.Order;
import br.com.gma.poc.order.domain.entity.OrderStatusEnum;
import br.com.gma.poc.order.domain.repository.OrderRepository;

@Service
public class ProcessarOrderRejeitadaServiceImpl implements ProcessarOrderRejeitadaService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	@RabbitListener(queues = { "${queue.order.rejeitada}" })
	@Override
	public void executar(@Payload String msg) {

		System.out.println("RECEBENDO=================================>" + msg);

		// TODO CRIRAR OBJETO MENSAGEM
		Long id = Long.parseLong(msg.replace("ORDER-ID:", "").trim());
	 

		// TOO tratar nao encontrada
		Order o = orderRepository.findById(id).get();
		o.setStatus(OrderStatusEnum.REJEITADA);
		orderRepository.save(o);
	}

}
