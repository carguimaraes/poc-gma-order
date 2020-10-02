package br.com.gma.poc.order.app.model;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_EMPTY)
public class OrderItemResource {

	private Long id;
	
	@NotEmpty(message = "[OrderItem] O campo nome eh obrigatorio")
	private String nome;
	
	@Valid
	@NotNull(message = "[OrderItem] O campo product eh obrigatorio")
	private ProductResource product;

	public OrderItemResource() {}
	public OrderItemResource(Long id, String nome) {
		this.id = id;
		this.nome = nome;
		 
	}

}
