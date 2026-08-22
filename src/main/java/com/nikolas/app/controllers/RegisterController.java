package com.nikolas.app.controllers;

import com.nikolas.app.components.EmailTemplates;
import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.controllers.forms.FormRegister;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterController {


    private int pageVisits = 0;

    @Autowired
    VisitsMetricsService visitsMetricsService;

    @Autowired
    private EmailTemplates emailTemplates;


    @GetMapping()
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model,++pageVisits);

        model.addAttribute("formRegister", new FormRegister());
        return "contact";
    }

    @PostMapping()
    public String handleRequest(Model model, @Valid @ModelAttribute("formRegister") FormRegister formRegister, BindingResult bindingResult) throws MessagingException, IOException {
        visitsMetricsService.increaseCounters(model,++pageVisits);

        if(!bindingResult.hasErrors()){
            model.addAttribute("success", true);

            //pros ton diaxeiristi tou pitospies
            //emailTemplates.sendEmailToAdminContactForm(formRegister);

            //pros ton user pu sumplirwnei thn form
            //emailTemplates.sendEmailToClientContactForm(formRegister);

        }

        return "contact";
    }

}
