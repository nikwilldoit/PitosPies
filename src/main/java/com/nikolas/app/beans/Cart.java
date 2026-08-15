package com.nikolas.app.beans;

import com.nikolas.app.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Cart {

    private Map<Product, Integer> cart;

    public Cart(){
        cart = new HashMap<>();
    }
}
