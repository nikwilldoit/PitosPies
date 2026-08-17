package com.nikolas.app.controllers;

import com.nikolas.app.services.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html-mail")
public class HtmlMailController {
    @Autowired
    private MailService mailService;

    @GetMapping
    public String handleRequest(Model model) throws MessagingException {
        mailService.sendHtmlEmail("nickstation007@gmail.com", "hey",
                "<p style=\"background-color: black;color: red;\">Hey there!</p>");
        return "done";
    }
}