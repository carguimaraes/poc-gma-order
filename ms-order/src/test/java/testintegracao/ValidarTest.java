package testintegracao;

import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.model.OrderResource;
import br.com.gma.poc.order.app.service.OrderResourceBuilder;
import br.com.gma.poc.order.infra.util.Validar;

public class ValidarTest {

	public static void main(String[] args) {
		executar();

	}

	public static void executar() {
		OrderResource oR = OrderResourceBuilder.build(true);

		try {
			Validar.executar(oR);
			System.out.println("VALIDACAO OK");
		} catch (DadoInvalidoException e) {
			System.out.println("VALIDACAO ERRO");
			for (String item : e.getErrors()) {
				System.out.println("====>" + item);
			}
		}

	}

}
