package br.com.gma.poc.payment.integracao;

import org.springframework.stereotype.Service;

@Service
public class ExternalGatewayMOCK implements ExternalGateway {

	@Override
	public boolean isAprovado(long id) {

		return id % 2 == 0;
	}

}
