package com.nikolas.app.controllers;

import com.nikolas.app.services.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-mail")
public class TextMailController {

    @Autowired
    private MailService mailService;

    @GetMapping()
    public String handleRequest(Model model) throws MessagingException {

        mailService.sendTextEmail("nickstation007@gmail.com", "hey", "hey");

        return "done";
    }
}
