package com.nikolas.app.controllers;

import com.nikolas.app.components.SessionData;
import com.nikolas.app.controllers.forms.FormDataOrder;
import com.nikolas.app.models.Ingredient;
import com.nikolas.app.models.Pie;
import com.nikolas.app.repositories.PieRepository;
import com.nikolas.app.models.Award;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private SessionData sessionData;

    @GetMapping()
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model,++pageVisitsPies);

        model.addAttribute("pies", pieRepository.findAll());
        return "pies";
    }

    @GetMapping("/{id}")
    public String handleRequest(Model model , @PathVariable Integer id) {
        visitsMetricsService.increasePieCounter(model, id);


        Pie pie = pieRepository.findPieById(id);
        pie.setIngredients(pieRepository.findIngredientsOfPie(id));

        model.addAttribute("pie", pie);
        return "pie";
    }

    @PostMapping("/{id}")
    public String handleRequest(Model model , @PathVariable Integer id, @RequestParam Integer quantity) {

        sessionData.getOrder().put(id,quantity);
        System.out.println(sessionData.getOrder());

        return "redirect:/buy";
    }
}
