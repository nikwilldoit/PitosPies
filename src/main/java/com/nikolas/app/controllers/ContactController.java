package com.nikolas.app.controllers;

import com.nikolas.app.beans.Counter;
import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private int pageVisits = 0;

    @Autowired
    VisitsMetricsService visitsMetricsService;

    @GetMapping()
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model,++pageVisits);

        model.addAttribute("formDataContact", new FormDataContact());
        return "contact";
    }

    @PostMapping()
    public String handleRequest(Model model, @Valid @ModelAttribute("formDataContact") FormDataContact formDataContact, BindingResult bindingResult) {
        visitsMetricsService.increaseCounters(model,++pageVisits);

        if(!bindingResult.hasErrors()){
            model.addAttribute("success", true);
        }

        return "contact";
    }
}
