package br.com.gma.poc.order.app.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

 
 
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CfgAdivice {
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler({ RuntimeException.class })
	public String[] handleRunTimeException(RuntimeException ex) {

		log.error("Exception : ", ex);

	 
		return new String[] { "Erro interno de processamento" };
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler({ Exception.class })
	public String[] handleRunTimeException(Exception ex) {

		log.error("Exception : ", ex);

	 
	 
		return new String[] { "Erro interno de processamento" };
	}

	@ResponseBody
	@ExceptionHandler(DadoInvalidoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String[] dadoInvalido(final DadoInvalidoException ex) {	

		 
		return  ex.getErrors();
	}

	@ResponseBody
	@ExceptionHandler(InformacaoNaoEncontradaException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String[] informacaoNaoEncontrada(final InformacaoNaoEncontradaException ex) {

		 
		return  ex.getErrors();
	}

	@ResponseBody
	@ExceptionHandler(FalhaAutenticacaoException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String[]  falhaAutenticacao(final FalhaAutenticacaoException ex) {

		 
		return  ex.getErrors();
	}

	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String[] violacaoDeConstraint(final ConstraintViolationException ex) {

	 
		return  new String[] { ex.getMessage() };
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String[] argumentosInvalidos(final MethodArgumentNotValidException ex) {
		
		String[] errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.toArray(String[]::new);

		 
		return errors;
	}
	 

}
