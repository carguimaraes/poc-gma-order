package br.com.gma.poc.order.app.model;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;

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
public class CustomerResource {
	
	private Long id;
 
	@NotEmpty(message = "[Customer] O campo nome eh obrigatorio", groups=VdGrpIncCustomerResource.class)
	private String nome;

	public CustomerResource() {}

	public CustomerResource(Long id, String nome) {
		
		this.id = id;
		this.nome = nome;
	}
	
}
