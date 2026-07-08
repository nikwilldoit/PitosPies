package com.nikolas.app.controllers;

import com.nikolas.app.models.Pie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pies")
public class PiesController {

//    @GetMapping()
//    public String handleRequest(){
//        return "pies";
//    }

    @GetMapping("/{id}")
    public String handleRequest(Model model , @PathVariable String id) {
        Pie p = new Pie();
        model.addAttribute("pie", p.getPie(Integer.parseInt(id)));
        return "pie";
    }

    @GetMapping()
    public String handleRequest(Model model) {
        Pie p = new Pie();
        model.addAttribute("pies", p.getPies());
        return "pies";
    }
}
