package com.nikolas.app.controllers;

import com.nikolas.app.components.EmailTemplates;
import com.nikolas.app.controllers.forms.FormRegister;
import com.nikolas.app.repositories.UserRepository;
import com.nikolas.app.models.User;
import com.nikolas.app.services.AuthService;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping
public class AdminController {

    @Autowired
    private AuthService authService;
    @Autowired
    private VisitsMetricsService visitsMetricsService;

    @Autowired
    private UserRepository userRepository;

    private int pageVisits = 0;


    @GetMapping("/admin")
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        model.addAttribute("unverifiedUsers", userRepository.cntUnverifiedUsers());
        return "admin";
    }

    @GetMapping("/admin/{action}")
    public String handleRequest2(Model model, @PathVariable String action) {
        if (action.equals("1")) {
            userRepository.deleteUnverifiedUsers();
        }
        return "forward:/admin";
    }
}
