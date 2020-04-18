package com.shop.ecommerce.affablebean.repository;

import com.shop.ecommerce.affablebean.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
