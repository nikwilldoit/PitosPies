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
    private String filename;

    public static Pie getPie(int id){
        switch (id){
            case 1:
                ArrayList<String> ingr = new ArrayList<>();
                ingr.add("Σπανάκι");
                ingr.add("Φέτα");
                return new Pie(1,"Σπανακόπιτα", 4.0, ingr,"/spanakopita.jpg");
            case 2:
                ArrayList<String> ingr2 = new ArrayList<>();
                ingr2.add("Μανιτάρια");
                ingr2.add("Βούτυρο");
                return new Pie(2,"Μανιταρόπιτα", 5.5, ingr2,"/manitaropita.jpg");
            case 3:
                ArrayList<String> ingr3 = new ArrayList<>();
                ingr3.add("Πράσα");
                ingr3.add("Φέτα");
                return new Pie(3,"Πρασόπιτα", 3.5, ingr3,"/prasopita.jpg");
            case 4:
                ArrayList<String> ingr4 = new ArrayList<>();
                ingr4.add("Κολοκύθια");
                ingr4.add("Πατάτες");
                return new Pie(4,"Μπουρέκι", 4.5, ingr4,"/boureki.jpg");

        }
        return null;
    }

    public static ArrayList<Pie> getPies(){
        ArrayList<Pie> pies = new ArrayList<>();
        for(int i=1; i<5; i++){
            pies.add(getPie(i));
        }
        return pies;
    }

//    public String getIngredientsAsString() {
//        return String.join(", ", ingredients);
//    }
}
