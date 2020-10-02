package br.com.gma.poc.order.app.service;

import org.springframework.messaging.handler.annotation.Payload;

public interface EmbarqueOrderService {

	public void enviar(  long orderId);
}
