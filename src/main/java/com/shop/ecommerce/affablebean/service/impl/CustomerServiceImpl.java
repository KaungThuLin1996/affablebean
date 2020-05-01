package com.shop.ecommerce.affablebean.service.impl;

import com.shop.ecommerce.affablebean.model.Customer;
import com.shop.ecommerce.affablebean.repository.CustomerRepository;
import com.shop.ecommerce.affablebean.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements BaseService<Customer, Long> {

    private final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer save(Customer entity) {
        return repository.save(entity);
    }

    @Override
    public Customer findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
