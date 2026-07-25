package com.nikolas.app.controllers;

import com.nikolas.app.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;


    @GetMapping("/{username}/{password}")
    public String handleRequest(Model model, @PathVariable String username, @PathVariable String password) {
        model.addAttribute("message", authService.registerUser(username,password));

        return "message";
    }
}