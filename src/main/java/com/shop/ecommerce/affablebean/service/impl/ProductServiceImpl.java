package com.shop.ecommerce.affablebean.service.impl;

import com.shop.ecommerce.affablebean.model.Product;
import com.shop.ecommerce.affablebean.repository.ProductRepository;
import com.shop.ecommerce.affablebean.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements BaseService<Product, Long> {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product entity) {
        return repository.save(entity);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
