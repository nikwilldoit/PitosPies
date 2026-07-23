package com.nikolas.app.controllers;

import com.nikolas.app.models.Pie;
import com.nikolas.app.repositories.PieRepository;
import com.nikolas.app.models.Award;
import com.nikolas.app.services.VisitsMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pies")
public class PiesController {

//    @GetMapping()
//    public String handleRequest(){
//        return "pies";
//    }

    @Autowired
    VisitsMetricsService visitsMetricsService;

    private int pageVisitsPies = 0;

    @Autowired
    PieRepository pieRepository;


    @GetMapping()
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model,++pageVisitsPies);

        model.addAttribute("pies", pieRepository.findAll());
        return "pies";
    }

    @GetMapping("/{id}")
    public String handleRequest(Model model , @PathVariable Integer id) {
        visitsMetricsService.increasePieCounter(model, id);

        Pie pie = pieRepository.findPiesById(id);

        List<Award> awards = pieRepository.findAwardsByPieId(id);

        pie.setAwards(awards);


        model.addAttribute("pie", pieRepository.findPiesById(id));
        return "pie";
    }
}
