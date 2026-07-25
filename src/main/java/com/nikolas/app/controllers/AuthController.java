package com.nikolas.app.controllers;

import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.repositories.UserRepository;
import com.nikolas.app.models.User;
import com.nikolas.app.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;

@Controller
public class AuthController {

    @Autowired
    private SessionBean sessionBean;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/register/{username}/{password}")
    public String handleRequest(Model model, @PathVariable String username, @PathVariable String password) {
        String message = authService.registerUser(username, password);

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/login/{username}/{password}")
    public String handleRequest(Model model){

        return "message";
    }
}