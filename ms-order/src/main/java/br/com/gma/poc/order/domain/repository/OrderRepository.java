package br.com.gma.poc.order.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gma.poc.order.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {

	 
	
}
