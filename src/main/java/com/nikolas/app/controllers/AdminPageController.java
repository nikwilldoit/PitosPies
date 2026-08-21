package com.nikolas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AdminPageController {

    @GetMapping("/admin-page")
    public String handleRequest(Model model) {
        return "admin-page";
    }

    @GetMapping("/administrator-page")
    public String handleRequest2(Model model) {
        return "administrator-page";
    }
}