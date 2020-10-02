package br.com.gma.poc.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

 
import br.com.gma.poc.order.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
