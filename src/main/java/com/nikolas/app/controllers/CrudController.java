package com.nikolas.app.controllers;

import com.nikolas.app.repositories.CarRepository;
import com.nikolas.app.repositories.CarRepository;
import com.nikolas.app.repositories.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/crud")
public class CrudController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public String handleRequest() {
        System.out.println("save: " + carRepository.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2"),
                        new Car(null, "Car 3")
                )));

        System.out.println("count: " + carRepository.count());
        System.out.println("exists: " + carRepository.existsById(1));
        System.out.println("findAll: " + carRepository.findAll());
        List<Integer> ids = new ArrayList<>();
        for (var car: carRepository.findAll()) ids.add(car.getId());
        System.out.println("findById: " + carRepository.findById(ids.get(0)));
        System.out.println("findAllById: " + carRepository.findAllById(List.of(ids.get(0),ids.get(1))));
        Car c = carRepository.findById(ids.get(0)).get();
        System.out.println("Car: " + c);
        c.setName("Car X");
        System.out.println("update: " + carRepository.save(c));
        carRepository.deleteById(ids.get(1));
        System.out.println("findAll: " + carRepository.findAll());
        carRepository.deleteAll();
        System.out.println("findAll: " + carRepository.findAll());
        return "done";
    }

}
