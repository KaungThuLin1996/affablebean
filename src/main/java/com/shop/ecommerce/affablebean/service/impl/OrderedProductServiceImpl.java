package com.shop.ecommerce.affablebean.service.impl;

import com.shop.ecommerce.affablebean.model.OrderedProduct;
import com.shop.ecommerce.affablebean.model.OrderedProductId;
import com.shop.ecommerce.affablebean.repository.OrderedProductRepository;
import com.shop.ecommerce.affablebean.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderedProductService")
public class OrderedProductServiceImpl implements BaseService<OrderedProduct, OrderedProductId> {

    private final OrderedProductRepository repository;

    public OrderedProductServiceImpl(OrderedProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderedProduct save(OrderedProduct entity) {
        return repository.save(entity);
    }

    @Override
    public OrderedProduct findById(OrderedProductId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<OrderedProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(OrderedProductId id) {
        repository.deleteById(id);
    }

    public List<OrderedProduct> findByCustomerOrderId(Long id) {
        return repository.findByCustomerOrderId(id);
    }
}
