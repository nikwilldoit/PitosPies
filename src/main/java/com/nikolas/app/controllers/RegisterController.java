package com.nikolas.app.controllers;

import com.nikolas.app.models.User;
import com.nikolas.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{username}/{password}")
    public String handleRequest(Model model, @PathVariable String username, @PathVariable String password) {
        User user = userRepository.findUserByUsername(username);
        if(user!=null){
            String m = "User "+ userRepository.findUserByUsername(username).getUsername() +" already exists!";
            model.addAttribute("message", m);
        }
        else{
            String m = "User "+ username +" just registered!";
            model.addAttribute("message", m);
            userRepository.save(new User(null,username,password,null));
        }
        System.out.println(userRepository.findAll());

        return "message";
    }
}

