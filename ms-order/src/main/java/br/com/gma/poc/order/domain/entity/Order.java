package br.com.gma.poc.order.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(schema = "ms_order" , name = "order_tb")
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Order {

	@Getter @Setter 
	@Id
	@SequenceGenerator(schema = "ms_order", name="order_tb_id_seq", sequenceName="order_tb_id_seq",  allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="order_tb_id_seq")
	@Column(name = "id", nullable = false)
    private Long id;

	@Getter @Setter
	@Column(name = "nome", nullable = false)
	private String nome;	 

	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_customer", referencedColumnName = "id")
	private Customer customer;
	
	@Getter @Setter
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_payment_details", referencedColumnName = "id")
	private PaymentDetails paymentDetails;
 
	
	 
	@Getter @Setter   
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_order")
	private List<OrderItem> orderItems = null;
	
	//TODO para implementar
	public void addOrderItem(OrderItem orderItem) {
		//orderItem.setOrder(this);
		//orderItems.add(orderItem);
	}
	
	@Getter @Setter   
	@Column(name = "situacao", nullable = false)
	@Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
	 
}
