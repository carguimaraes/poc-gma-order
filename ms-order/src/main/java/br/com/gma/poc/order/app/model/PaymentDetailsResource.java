package br.com.gma.poc.order.app.model;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_EMPTY)
public class PaymentDetailsResource {
	
	private Long id;
	 
	@NotEmpty(message = "[PaymentDetails] O campo nome eh obrigatorio",groups = VdGrpIncPaymentDetailsResource.class)
	private String nome;

	public PaymentDetailsResource() {}
	
	public PaymentDetailsResource(Long id,String nome) {
		this.id = id;
		this.nome = nome;
	}
}
