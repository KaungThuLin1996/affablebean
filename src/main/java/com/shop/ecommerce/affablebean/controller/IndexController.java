package com.shop.ecommerce.affablebean.controller;

import com.shop.ecommerce.affablebean.model.Category;
import com.shop.ecommerce.affablebean.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("categoryService")
    private CategoryServiceImpl categoryService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "pages/index";
    }
}
