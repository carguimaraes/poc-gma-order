package br.com.gma.poc.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gma.poc.order.domain.entity.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>  {

}
