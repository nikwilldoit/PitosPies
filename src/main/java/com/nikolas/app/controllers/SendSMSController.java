package com.nikolas.app.controllers;

import com.nikolas.app.services.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//THE SMS PART IS UNDER CONSTRUCTION
//IN ORDER TO FIND AN APP BETTER THAN twilio
//TO REDUCE COSTS

@Controller
@RequestMapping("/send-sms")
public class SendSMSController {

    @Autowired
    SMSService smsService;

    @GetMapping
    public String handleRequest(Model model){

        smsService.send("+from_tel", "+to_tel", "This a sample SMS!");
        return "index";
    }
}
