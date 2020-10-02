package br.com.gma.poc.order.infra.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import br.com.gma.poc.order.app.exception.DadoInvalidoException;
import br.com.gma.poc.order.app.model.VdGrpIncCustomerResource;

public class Validar {
	
	//TODO 
	//Class<?>... groups
	public static <T> void executar(T orderResource) throws DadoInvalidoException {
		 
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		 
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(orderResource);
		
		if(!constraintViolations.isEmpty()) {
			String[] errors = constraintViolations
					.stream()
					.map(x -> x.getMessage())
					.toArray(String[]::new);

			throw new DadoInvalidoException(errors);
		}
		
		 
		
		 

	}

}
