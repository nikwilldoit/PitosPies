package com.nikolas.app.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class SessionBean {

    Map<String, Integer> cart;

    public SessionBean(){
        cart = new HashMap<>();
    }

}
