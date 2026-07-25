package com.nikolas.app.controllers;

import com.nikolas.app.beans.SessionBean;
import com.nikolas.app.repositories.UserRepository;
import com.nikolas.app.models.User;
import com.nikolas.app.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String handleRequest(Model model, HttpSession session, @PathVariable String username, @PathVariable String password, HttpServletResponse response) {
        String message = authService.loginUser(session, username, password);

        // START: EXERCISE 3
        ResponseCookie cookie = ResponseCookie.from("session", session.getId()).maxAge(Duration.ofDays(30)).path("/").build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        // END: EXERCISE 3

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/logout")
    public String handleRequest(Model model) {
        String message = authService.logoutUser();

        model.addAttribute("message", message);

        return "message";
    }

    @GetMapping("/resource")
    public String handleRequest(Model model, HttpSession session, @CookieValue(name="session", defaultValue = "missing") String sessionCookie) {
        String message;
        if (!authService.activeSession()) {
            message = "You have to login!";

            if (!sessionCookie.equals("missing")){
                System.out.println(sessionCookie);
                User user = userRepository.findUserBySession(sessionCookie);
                System.out.println(user);
                if (user!=null) {
                    user.setSession(session.getId());
                    userRepository.save(user);
                    sessionBean.setUser(user);
                    message = sessionBean.getUser().getUsername() + "'s Resource";
                }
            }
        }
        else {
            message = sessionBean.getUser().getUsername() + "'s Resource";
        }
        model.addAttribute("message", message);

        return "message";
    }
}