package com.shop.ecommerce.affablebean.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.ecommerce.affablebean.model.*;
import com.shop.ecommerce.affablebean.service.impl.CustomerOrderServiceImpl;
import com.shop.ecommerce.affablebean.service.impl.CustomerServiceImpl;
import com.shop.ecommerce.affablebean.service.impl.OrderedProductServiceImpl;
import com.shop.ecommerce.affablebean.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class CartController {

    @Autowired
    @Qualifier("productService")
    private ProductServiceImpl productService;

    @Autowired
    @Qualifier("customerService")
    private CustomerServiceImpl customerService;

    @Autowired
    @Qualifier("customerOrderService")
    private CustomerOrderServiceImpl customerOrderService;

    @Autowired
    @Qualifier("orderedProductService")
    private OrderedProductServiceImpl orderedProductService;

    @GetMapping("/category/{catId}/cart")
    public String viewCart(@PathVariable("catId") String catId, Model model) {
        model.addAttribute("catId", catId);
        return "pages/cart/cart";
    }

    @PostMapping("/cart")
    public @ResponseBody String processCart(@RequestBody String requestItems) {
        List<Product> products = new ArrayList<>();
        CartItem[] cartItems;
        String responseItems = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            cartItems = mapper.readValue(requestItems, CartItem[].class);
            for (CartItem cartItem : cartItems) {
                products.add(productService.findById(cartItem.getId()));
            }
            responseItems = mapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return responseItems;
    }

    @GetMapping("/cart/checkout/{catId}")
    public String showCheckout(@PathVariable("catId") String catId, Model model) {
        model.addAttribute("catId", catId);
        model.addAttribute("customer", new Customer());
        return "pages/cart/checkout";
    }

    @PostMapping("/cart/checkout")
    public String processCheckout(@ModelAttribute("customer") Customer customer,
                                  @RequestParam("gTotal") BigDecimal gTotal,
                                  @RequestParam("cartItemListString") String cartItemListString,
                                  Model model) {
        if (customer != null) {
            customerService.save(customer);
        }
        Random rand = new Random();
        int num = rand.nextInt(10000) + 1;
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setAmount(gTotal);
        customerOrder.setConfirmationNumber(num);
        customerOrder.setDateCreated(LocalDate.now());
        customerOrder.setCustomer(customer);
        customerOrderService.save(customerOrder);

        CartItem[] cartItems;
        ObjectMapper mapper = new ObjectMapper();
        BigDecimal totalCost = BigDecimal.valueOf(0);
        try {
            cartItems = mapper.readValue(cartItemListString, CartItem[].class);
            for (CartItem cartItem : cartItems) {
                Product product = productService.findById(cartItem.getId());
                totalCost = totalCost.add(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getCount())));
                OrderedProductId id = new OrderedProductId(customerOrder.getId(), product.getId());
                OrderedProduct orderedProduct = new OrderedProduct();
                orderedProduct.setId(id);
                orderedProduct.setCustomerOrder(customerOrder);
                orderedProduct.setProduct(product);
                orderedProduct.setQuantity(cartItem.getCount());
                orderedProductService.save(orderedProduct);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<OrderedProduct> orderedProducts = orderedProductService.findByCustomerOrderId(customerOrder.getId());
        model.addAttribute("customerOrder", customerOrder);
        model.addAttribute("orderedProducts", orderedProducts);
        model.addAttribute("totalCost", totalCost);
        return "pages/confirmation";
    }
}
