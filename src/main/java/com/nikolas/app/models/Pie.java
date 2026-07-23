package com.nikolas.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @MappedCollection(idColumn = "pie_id", keyColumn = "order")
    private List<Award> awards;

    @Transient
    private List<String> ingredients;


//    public String getIngredientsAsString() {
//        return String.join(", ", ingredients);
//    }
}
