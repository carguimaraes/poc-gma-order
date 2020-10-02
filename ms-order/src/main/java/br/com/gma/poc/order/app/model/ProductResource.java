package br.com.gma.poc.order.app.model;

import java.util.List;

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
public class ProductResource {

	private Long id;
    //VdGrpIncProductResource
	@NotEmpty(message = "[Product] O campo nome eh obrigatorio", groups = VdGrpIncProductResource.class)
	private String nome;
	
	public ProductResource() {}

	public ProductResource(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

}
