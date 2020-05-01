package com.shop.ecommerce.affablebean.controller;

import com.shop.ecommerce.affablebean.model.Product;
import com.shop.ecommerce.affablebean.service.impl.CategoryServiceImpl;
import com.shop.ecommerce.affablebean.service.impl.ProductServiceImpl;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")
    private ProductServiceImpl productService;

    @Autowired
    @Qualifier("categoryService")
    private CategoryServiceImpl categoryService;

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "pages/product/productForm";
    }

    @PostMapping("/new")
    public String processProductForm(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file) {
        product.setLastUpdate(LocalDate.now());
        product.setImage(getByteArray(file));
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("{id}/image")
    public void renderImageFromDb(@PathVariable("id") Long id, HttpServletResponse response) {
        Product product = productService.findById(id);
        byte[] byteArray = new byte[product.getImage().length];
        int i = 0;
        for (Byte b : product.getImage()) {
            byteArray[i++] = b;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        try {
            IOUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Byte[] getByteArray(MultipartFile file) {
        Byte[] byteArray = null;
        try {
            byteArray = new Byte[file.getBytes().length];
            int i = 0;
            for (Byte b : file.getBytes()) {
                byteArray[i++] = b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }
}
