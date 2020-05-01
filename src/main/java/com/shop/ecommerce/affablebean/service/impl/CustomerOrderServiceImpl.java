package com.shop.ecommerce.affablebean.service.impl;

import com.shop.ecommerce.affablebean.model.CustomerOrder;
import com.shop.ecommerce.affablebean.repository.CustomerOrderRepository;
import com.shop.ecommerce.affablebean.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerOrderService")
public class CustomerOrderServiceImpl implements BaseService<CustomerOrder, Long> {

    private final CustomerOrderRepository repository;

    public CustomerOrderServiceImpl(CustomerOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerOrder save(CustomerOrder entity) {
        return repository.save(entity);
    }

    @Override
    public CustomerOrder findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<CustomerOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
