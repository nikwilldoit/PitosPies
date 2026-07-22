package com.nikolas.app.controllers;


import com.nikolas.app.repositories.PersonRepository;
import com.nikolas.app.repositories.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/query1")
    public String handleRequest() {
        List<Person> persons = personRepository.customQuery();
        System.out.println(persons);
        return "done";
    }

    @GetMapping("/query2")
    public String handleRequest2() {
        List<Person> persons = personRepository.customQuery2(1000);
        System.out.println(persons);
        return "done";
    }

}
