package com.nikolas.app.controllers;

import com.nikolas.app.repositories.CarRepository2;
import com.nikolas.app.repositories.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cud")
public class CUDController {
    @Autowired
    private CarRepository2 carRepository2;

    @GetMapping("/insert")
    public String handleRequest2() {
        System.out.println("save many: " + carRepository2.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2"),
                        new Car(null, "Car 3")
                )));

        System.out.println("save one: " + carRepository2.save(
                new Car(null, "Car 4")));

        System.out.println(carRepository2.findAll());

        carRepository2.deleteAll();

        System.out.println(carRepository2.findAll());
        return "done";
    }

    @GetMapping("/update")
    public String handleRequest3() {
        carRepository2.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2"),
                        new Car(null, "Car 3")
                ));

        // modify 1
        Car car = carRepository2.findCarByName("Car 2");
        car.setName("Car 12");
        carRepository2.save(car);
        System.out.println(carRepository2.findAll());

        // modify 2
        car.setName("Car 22");
        Car car2 = carRepository2.findCarByName("Car 3");
        car2.setName("Car 13");
        carRepository2.saveAll(List.of(car, car2));
        System.out.println(carRepository2.findAll());

        carRepository2.deleteAll();
        return "done";
    }

    @GetMapping("/delete")
    public String handleRequest4() {
        carRepository2.saveAll(
                List.of(new Car(null, "Car 1"),
                        new Car(null, "Car 2"),
                        new Car(null, "Car 3")
                ));

        // delete 1
        Car car = carRepository2.findCarByName("Car 2");
        carRepository2.delete(car);
        System.out.println(carRepository2.findAll());

        // delete 2
        car = carRepository2.findCarByName("Car 1");
        Car car2 = carRepository2.findCarByName("Car 3");
        carRepository2.deleteAll(List.of(car, car2));
        System.out.println(carRepository2.findAll());

        carRepository2.deleteAll();
        return "done";
    }
}