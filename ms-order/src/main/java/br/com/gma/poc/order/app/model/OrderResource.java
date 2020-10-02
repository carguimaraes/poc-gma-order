package br.com.gma.poc.order.app.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.gma.poc.order.domain.entity.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

 
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_EMPTY)
public class OrderResource {

	
	private Long id;

	@NotEmpty(message = "[Order] O campo nome eh obrigatorio")
	private String nome=null;
	
	private String status=null;
	
	@NotEmpty(message = "[Order] O campo observacao eh obrigatorio")
	private String observacao=null;

	@Valid
	@NotNull(message = "[Order] O campo customer eh obrigatorio")
	private  CustomerResource customer=null; 
	
	@Valid
	@NotNull(message = "[Order] O campo paymentDetails eh obrigatorio")
	private PaymentDetailsResource paymentDetails;
	
	@Valid
	@NotEmpty(message = "[Order] O campo orderItems eh obrigatorio")
	private List<OrderItemResource> orderItems ;

 
}
