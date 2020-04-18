package com.shop.ecommerce.affablebean.service;

import java.util.List;

public interface BaseService<T, U> {

    T save(T entity);
    T findById(U id);
    List<T> findAll();
    void deleteById(U id);

}
