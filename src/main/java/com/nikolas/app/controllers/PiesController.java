package com.nikolas.app.controllers;

import com.nikolas.app.beans.Counter;
import com.nikolas.app.models.Pie;
import org.springframework.beans.factory.annotation.Autowired;
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

    private int pageVisitsPies = 0;

    private int pageVisitsPie = 0;

    @Autowired
    private Counter totalVisitsCounter;

    @GetMapping("/{id}")
    public String handleRequest(Model model , @PathVariable String id) {
        pageVisitsPie++;
        totalVisitsCounter.increase();
        model.addAttribute("pageVisits",  pageVisitsPie);

        model.addAttribute("pie", Pie.getPie(Integer.parseInt(id)));
        return "pie";
    }

    @GetMapping()
    public String handleRequest(Model model) {
        pageVisitsPies++;
        totalVisitsCounter.increase();
        model.addAttribute("pageVisits",  pageVisitsPies);

        model.addAttribute("pies", Pie.getPies());
        return "pies";
    }
}
