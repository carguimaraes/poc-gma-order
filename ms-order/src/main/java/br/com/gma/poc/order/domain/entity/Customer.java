package br.com.gma.poc.order.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Table(schema = "ms_order" , name = "customer_tb")
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {

	@Id
	@SequenceGenerator(schema = "ms_order", name="customer_tb_id_seq", sequenceName="customer_tb_id_seq",  allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="customer_tb_id_seq")
	@Column(name = "id", nullable = false)
    private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;	 

 
	
}
