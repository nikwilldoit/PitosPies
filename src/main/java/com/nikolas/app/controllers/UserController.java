package com.nikolas.app.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {


    @GetMapping("/error")
    public void handleRequest() {
        throw new NullPointerException();
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String handleException(NullPointerException ex, Model model) {
        model.addAttribute("status", 400);
        model.addAttribute("message", "NullPointerException");
        return "error";
    }
}
