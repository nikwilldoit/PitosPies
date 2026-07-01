package com.nikolas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attr")
public class AttrController {

    @GetMapping()
    public String handleRequest() {
        return "attr";
    }
}
