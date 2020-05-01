package com.shop.ecommerce.affablebean.repository;

import com.shop.ecommerce.affablebean.model.OrderedProduct;
import com.shop.ecommerce.affablebean.model.OrderedProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepository extends JpaRepository<OrderedProduct, OrderedProductId> {
    List<OrderedProduct> findByCustomerOrderId(Long id);
}
