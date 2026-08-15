package com.nikolas.app.controllers;

import com.nikolas.app.beans.Cart;
import com.nikolas.app.models.Product;
import com.nikolas.app.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class CartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Cart cart;

    @PostMapping("/cart/add")
    public String handleRequest(Model model, @ModelAttribute("productId") Integer productId) {

        Product product = productRepository.findProductById(productId);
        cart.getCart().put(product, 1);

        System.out.println("The Cart: ");
        for (var item: cart.getCart().keySet()) {
            System.out.println(item + ":  " + cart.getCart().get(item));
        }

        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String handleRequest(Model model) {

        double totalPrice = 0;

        for (Product product: cart.getCart().keySet()) {
            totalPrice+=product.getPrice() * cart.getCart().get(product);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }
}
