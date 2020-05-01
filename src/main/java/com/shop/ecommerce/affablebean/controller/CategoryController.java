package com.shop.ecommerce.affablebean.controller;

import com.shop.ecommerce.affablebean.model.Category;
import com.shop.ecommerce.affablebean.model.Product;
import com.shop.ecommerce.affablebean.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    @Qualifier("categoryService")
    private CategoryServiceImpl categoryService;

    @GetMapping("/{catId}")
    public String showCategory(@PathVariable("catId") Long catId, Model model) {
        List<Category> categories = categoryService.findAll();
        Category category = categoryService.findById(catId);
        List<Product> products = category.getProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("catId", catId);
        return "pages/category/category";
    }
}
