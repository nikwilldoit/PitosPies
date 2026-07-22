//package com.nikolas.app.controllers;
//
//import com.nikolas.app.models.Person;
//import com.nikolas.app.repositories.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class TestController {
//
//    @Autowired
//    private PersonRepository personRepository;
//
//    @GetMapping("/test")
//    public String handleRequest() {
//        System.out.println(personRepository.getAllUsers());
//        return "done";
//    }
//}
