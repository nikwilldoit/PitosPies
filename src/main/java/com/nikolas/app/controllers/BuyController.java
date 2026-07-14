package com.nikolas.app.controllers;

import com.nikolas.app.beans.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class BuyController {

    private int pageVisits = 0;

    @Autowired
    private Counter totalVisitsCounter;

    @GetMapping()
    public String handleRequest(Model model) {
        pageVisits++;
        totalVisitsCounter.increase();
        model.addAttribute("pageVisits",  pageVisits);
        return "buy";
    }
}
