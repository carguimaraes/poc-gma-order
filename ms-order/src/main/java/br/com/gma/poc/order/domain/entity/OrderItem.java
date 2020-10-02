package br.com.gma.poc.order.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(schema = "ms_order" , name = "order_item_tb")
 
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderItem {

	
	@Getter @Setter(value = AccessLevel.PRIVATE)  
	@Id 
	@SequenceGenerator(schema = "ms_order", name="order_item_tb_id_seq", sequenceName="order_item_tb_id_seq",  allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="order_item_tb_id_seq")
	@Column(name = "id", nullable = false)
    private Long id=0L;

	@Getter @Setter
	@Column(name = "nome", nullable = false)
	private String nome;	 

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_product", referencedColumnName = "id")
	private Product product;
 
	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Order order;
    
	 
}
