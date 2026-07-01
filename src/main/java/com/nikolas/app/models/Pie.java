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
    private ArrayList<String> ingredients;

    public Pie getPie(int id){
        switch (id){
            case 1:
                ArrayList<String> ingr = new ArrayList<>();
                ingr.add("Spanaki");
                ingr.add("Feta");
                return new Pie(1,"Spanakopita", 4.0, ingr);
            case 2:
                ArrayList<String> ingr2 = new ArrayList<>();
                ingr2.add("Manitaria");
                ingr2.add("Bouturo");
                return new Pie(2,"Manitaropita", 5.5, ingr2);
            case 3:
                ArrayList<String> ingr3 = new ArrayList<>();
                ingr3.add("Prasa");
                ingr3.add("Feta");
                return new Pie(3,"Prasopita", 3.5, ingr3);
            case 4:
                ArrayList<String> ingr4 = new ArrayList<>();
                ingr4.add("Kolokithia");
                ingr4.add("Patates");
                return new Pie(4,"Mpoureki", 4.5, ingr4);

        }
        return null;
    }
}
