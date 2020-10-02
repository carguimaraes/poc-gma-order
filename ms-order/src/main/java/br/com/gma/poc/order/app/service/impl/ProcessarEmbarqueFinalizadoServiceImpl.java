package br.com.gma.poc.order.app.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gma.poc.order.app.service.EmbarqueOrderService;
import br.com.gma.poc.order.app.service.ProcessarEmbarqueFinalizadoService;
import br.com.gma.poc.order.domain.entity.Order;
import br.com.gma.poc.order.domain.entity.OrderStatusEnum;
import br.com.gma.poc.order.domain.repository.OrderRepository;

@Service
public class ProcessarEmbarqueFinalizadoServiceImpl implements ProcessarEmbarqueFinalizadoService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private EmbarqueOrderService embarqueOrderService;

	@Transactional
	@RabbitListener(queues = { "${queue.order.embarquefinalizado}" })
	@Override
	public void executar(String msg) {

		// TODO CRIRAR OBJETO MENSAGEM
		Long id = Long.parseLong(msg.replace("ORDER-ID:", "").trim());

		// TOO tratar nao encontrada
		Order o = orderRepository.findById(id).get();
		o.setStatus(OrderStatusEnum.CLIENTENOTIFICADO);
		orderRepository.save(o);

	}

}
