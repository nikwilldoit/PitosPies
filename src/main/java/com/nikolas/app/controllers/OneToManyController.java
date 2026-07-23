package com.nikolas.app.controllers;

import com.nikolas.app.repositories.PersonRepository;
import com.nikolas.app.repositories.entities.Car;
import com.nikolas.app.repositories.entities.Identity;
import com.nikolas.app.repositories.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/one-to-one")
public class OneToManyController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String handleRequest() {
        Person person = new Person(null, "Chloe", "O'Brien", 1000, null,null, null);
        Identity identity = new Identity(null, "AAAA1", "BBBB1");
        Set<Car> cars = Set.of(new Car(null, "Car 1"),new Car(null, "Car 2"),new Car(null, "Car 5"));
        person.setIdentity(identity);
        person.setCars(cars);

        personRepository.save(person);

        System.out.println(personRepository.findAll());

//        personRepository.delete(person);
//
//        System.out.println(personRepository.findAll());
        return "done";
    }
}