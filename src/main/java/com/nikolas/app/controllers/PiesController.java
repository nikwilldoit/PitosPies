package com.nikolas.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pies")
public class PiesController {

    @GetMapping()
    public String handleRequest(){
        return "pies";
    }

    @GetMapping("/{id}")
    public String handleRequest(Model model , @PathVariable String id) {
        model.addAttribute("id", id);
        return "error";
    }
}
