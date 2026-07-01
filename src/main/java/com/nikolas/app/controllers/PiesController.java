package com.nikolas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pies")
public class PiesController {

    @GetMapping()
    public String handleRequest() {
        return "pies";
    }
}
