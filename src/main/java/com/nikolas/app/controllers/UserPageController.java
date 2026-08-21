package com.nikolas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-page")
public class UserPageController {

    @GetMapping
    public String handleRequest(Model model) {
        return "user-page";
    }
}