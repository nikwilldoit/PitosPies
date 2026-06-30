package com.nikolas.app.controllers;

import com.nikolas.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String handleRequest(Model model) {
        User user = new User();
        user.setName("Nikolas");
        user.setId(1);
        model.addAttribute("user", user);
        return "page";
    }

    @GetMapping("/url")
    public String handleRequest2() {
        return "page";
    }
}