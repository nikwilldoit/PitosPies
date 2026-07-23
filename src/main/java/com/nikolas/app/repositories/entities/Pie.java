package com.nikolas.app.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Pie {
    @Id
    private int id;
    private String name;
    private double price;
    private String filename;
    private List<Award> awards;
    //private ArrayList<String> ingredients;


//    public String getIngredientsAsString() {
//        return String.join(", ", ingredients);
//    }
}
