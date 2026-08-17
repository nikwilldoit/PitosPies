package com.nikolas.app.controllers;

import com.nikolas.app.services.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/send-sms")
public class SendSMSController {

    @Autowired
    SMSService smsService;

    @GetMapping
    public String handleRequest(Model model){

        smsService.send("+tel", "+tel", "This a sample SMS!");
        return "done";
    }
}
