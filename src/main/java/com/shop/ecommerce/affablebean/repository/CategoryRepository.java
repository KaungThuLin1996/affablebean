package com.shop.ecommerce.affablebean.repository;

import com.shop.ecommerce.affablebean.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
