package com.nikolas.app.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WelcomeController {
    @GetMapping("/welcome")
    public String handleRequest(Model model, @AuthenticationPrincipal UserDetails userDetails, Authentication authentication) {
        model.addAttribute("userDetails", userDetails);
        model.addAttribute("authentication", authentication);
        return "welcome";
    }
}
