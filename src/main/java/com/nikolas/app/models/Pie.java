package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pie {
    private int id;
    private String name;
    private double price;
    private String filename;
    private ArrayList<String> ingredients;


//    public String getIngredientsAsString() {
//        return String.join(", ", ingredients);
//    }
}
