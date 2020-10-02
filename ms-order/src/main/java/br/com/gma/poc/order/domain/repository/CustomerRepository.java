package br.com.gma.poc.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gma.poc.order.domain.entity.Customer;
 

public interface CustomerRepository extends JpaRepository<Customer, Long>  {

}
