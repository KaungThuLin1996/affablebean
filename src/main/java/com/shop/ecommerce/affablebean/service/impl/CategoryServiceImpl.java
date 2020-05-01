package com.shop.ecommerce.affablebean.service.impl;

import com.shop.ecommerce.affablebean.model.Category;
import com.shop.ecommerce.affablebean.repository.CategoryRepository;
import com.shop.ecommerce.affablebean.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements BaseService<Category, Long> {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category save(Category entity) {
        return repository.save(entity);
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
