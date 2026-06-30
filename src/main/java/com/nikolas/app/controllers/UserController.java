package com.nikolas.app.controllers;

import com.nikolas.app.models.User;
import com.nikolas.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String handleRequest(Model model, @RequestParam("id") String id) {
        User user = userService.getUser(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "user";
    }
}
