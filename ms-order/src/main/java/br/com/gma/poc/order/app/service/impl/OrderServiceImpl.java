package br.com.gma.poc.order.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.exception.InformacaoNaoEncontradaException;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.service.MapperOrder;
import br.com.gma.poc.order.app.service.OrderService;
import br.com.gma.poc.order.app.service.OrderAprovacaoService;
import br.com.gma.poc.order.domain.entity.Customer;
import br.com.gma.poc.order.domain.entity.Order;
import br.com.gma.poc.order.domain.entity.OrderItem;
import br.com.gma.poc.order.domain.entity.OrderStatusEnum;
import br.com.gma.poc.order.domain.entity.PaymentDetails;
import br.com.gma.poc.order.domain.entity.Product;
import br.com.gma.poc.order.domain.repository.CustomerRepository;
import br.com.gma.poc.order.domain.repository.OrderRepository;
import br.com.gma.poc.order.domain.repository.PaymentDetailsRepository;
import br.com.gma.poc.order.domain.repository.ProductRepository;
import br.com.gma.poc.order.infra.util.Validar;

import org.springframework.amqp.core.Queue;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private MapperOrder mapperOrder;

	@Autowired
	private OrderAprovacaoService orderAprovacaoService;
	 

	@Override
	public OrderResource obterPorId(Long id) throws InformacaoNaoEncontradaException {

		Order o = orderRepository.findById(id).orElseThrow(() -> new InformacaoNaoEncontradaException());

		return mapperOrder.toOrderResource(o);
	}

	@Transactional
	@Override
	public OrderResource incluir(OrderResource orderResource) throws DadoInvalidoException {

		// --- VALIDAR orderResource
		Validar.executar(orderResource);

		Order o = mapperOrder.toOrder(orderResource);

		// TODO ESTA PARTE PODERIA ESTAR OrderService DE CAMADA DE DOMAIN

		// --- VALIDAR existencia (id) CustomerResource
		List<String> erros = new ArrayList<>();

		Customer cusT = customerRepository.findById(o.getCustomer().getId()).orElseGet(() -> {
			erros.add("Cliente id:" + orderResource.getCustomer().getId() + " não encontrado");
			return null;
		});

		// --- VALIDAR existencia (id) PaymentDetailsResource
		PaymentDetails pD = paymentDetailsRepository.findById(o.getPaymentDetails().getId()).orElseGet(() -> {
			erros.add("PaymentDetails id:" + orderResource.getPaymentDetails().getId() + " não encontrado");
			return null;
		});

		// --- VALIDAR validar existencia (id) ProductResource em
		// List<OrderItemResource>
		List<OrderItem> orderItems = new ArrayList<>();
		List<OrderItem> listOrderItem = o.getOrderItems();
		for (OrderItem item : listOrderItem) {

			Product pr = productRepository.findById(item.getProduct().getId()).orElseGet(() -> {
				erros.add("Product id:" + item.getProduct().getId() + " não encontrado");
				return null;
			});

			// TODO melhorar
			item.setOrder(o);

		}

		if (!erros.isEmpty()) {
			throw new DadoInvalidoException(erros.stream().toArray(String[]::new));
		}

		o.setId(0L);
		o.setStatus(OrderStatusEnum.EMAPROVACAO);
		Order oNova = orderRepository.save(o);

		//TODO criar estrutura de objeto para send com payload
		orderAprovacaoService.enviar(oNova.getId());
		 

		return mapperOrder.toOrderResource(oNova);
	}
 
	 

}
