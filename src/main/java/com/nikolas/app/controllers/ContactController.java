package com.nikolas.app.controllers;

import com.nikolas.app.beans.Counter;
import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.services.VisitsMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        FormDataContact formDataContact = new FormDataContact();
        visitsMetricsService.increaseCounters(model,++pageVisits);

        model.addAttribute("formDataContact", formDataContact);
        return "contact";
    }

    @PostMapping()
    public String handleRequest(Model model, @ModelAttribute("formDataContact") FormDataContact formDataContact) {
        if(!model.asMap().isEmpty()){
            System.out.println(model.asMap().values());
            model.addAttribute("success", true);
        }
        else{
            model.addAttribute("success", false);
        }

        return "contact";
    }
}
