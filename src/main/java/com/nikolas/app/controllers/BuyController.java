package com.nikolas.app.controllers;

import com.nikolas.app.components.EmailTemplates;
import com.nikolas.app.components.SessionData;
import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.controllers.forms.FormDataOrder;
import com.nikolas.app.models.Area;
import com.nikolas.app.models.Order;
import com.nikolas.app.models.Pie;
import com.nikolas.app.repositories.AreaRepository;
import com.nikolas.app.repositories.OrderRepository;
import com.nikolas.app.repositories.PieRepository;
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
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {

    private int pageVisits = 0;

    @Autowired
    private VisitsMetricsService visitsMetricsService;

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private PieRepository pieRepository;

    @Autowired
    private SessionData sessionData;

    @Autowired
    private EmailTemplates emailTemplates;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String handleRequest(Model model) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        List<Area> areas = (List<Area>) areaRepository.findAll();
        List<Pie> pies = (List<Pie>) pieRepository.findAll();
        model.addAttribute("areas", areas);
        model.addAttribute("pies", pies);

        FormDataOrder formDataOrder = new FormDataOrder();
        formDataOrder.setOrder(sessionData.getOrder());
        model.addAttribute("formDataOrder", formDataOrder);

        return "buy";
    }

    @PostMapping
    public String handleRequest(Model model, @Valid @ModelAttribute("formDataOrder") FormDataOrder formDataOrder,
                                BindingResult bindingResult) throws IOException, MessagingException {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        // session data needs updating with form changes
        sessionData.setOrder(formDataOrder.getOrder());

        // get the current timestamp
        formDataOrder.setStamp(LocalDateTime.now());

        // check for errors
        if (!bindingResult.hasErrors()) {
            model.addAttribute("success", true);

            //pros ton diaxeiristi tou pitospies
            emailTemplates.sendEmailToAdminOrderForm(formDataOrder, sessionData.getOrder());
            //pros ton user pu sumplirwnei thn form
            emailTemplates.sendEmailToClientOrderForm(formDataOrder, sessionData.getOrder());

            Order order = new Order(formDataOrder, sessionData.getOrder());
            orderRepository.save(order);
        }

        // send the data
        List<Area> areas = (List<Area>) areaRepository.findAll();
        List<Pie> pies = (List<Pie>) pieRepository.findAll();
        model.addAttribute("areas", areas);
        model.addAttribute("pies", pies);

        System.out.println(pies);

        return "buy";
    }

}