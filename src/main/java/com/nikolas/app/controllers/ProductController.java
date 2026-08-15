package com.nikolas.app.controllers;

import com.nikolas.app.beans.Cart;
import com.nikolas.app.models.Product;
import com.nikolas.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cart cart;

    @GetMapping("/products")
    public String handleRequest(Model model) {

        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/product/{id}")
    public String handleRequest(Model model, @PathVariable Integer id) {

        Product product = productRepository.findProductById(id);

        model.addAttribute("product", product);

        return "product";
    }

}
