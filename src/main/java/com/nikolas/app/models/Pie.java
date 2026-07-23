package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Table("pie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pie {
    @Id
    private int id;
    private String name;
    private double price;
    private String filename;
    //private ArrayList<String> ingredients;

    @MappedCollection(idColumn = "pie_id", keyColumn = "order")
    private List<Award> awards;


//    public String getIngredientsAsString() {
//        return String.join(", ", ingredients);
//    }
}
