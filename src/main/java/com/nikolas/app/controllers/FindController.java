package com.nikolas.app.controllers;

import com.nikolas.app.repositories.CarRepository;
import com.nikolas.app.repositories.CarRepository2;
import com.nikolas.app.repositories.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/find")
public class FindController {
    @Autowired
    private CarRepository2 carRepository2;

    @GetMapping
    public String handleRequest() {
        System.out.println("save: " + carRepository2.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2")
                )));
        Car c = carRepository2.findCarByName("Car 2");
        System.out.println(c);
        carRepository2.deleteAll();
        return "done";
    }

    @GetMapping("/like")
    public String handleRequest2() {
        System.out.println("save: " + carRepository2.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2")
                )));
        List<Car> cars = carRepository2.findCarByNameLike("Car%");
        System.out.println(cars);
        carRepository2.deleteAll();
        return "done";
    }


}