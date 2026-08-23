package com.nikolas.app.controllers;

import com.nikolas.app.components.EmailTemplates;
import com.nikolas.app.components.SessionData;
import com.nikolas.app.controllers.forms.FormLogin;
import com.nikolas.app.controllers.forms.FormPasswordReset;
import com.nikolas.app.controllers.forms.FormPasswordReset2;
import com.nikolas.app.controllers.forms.FormRegister;
import com.nikolas.app.models.User;
import com.nikolas.app.repositories.UserRepository;
import com.nikolas.app.services.AuthService;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private VisitsMetricsService visitsMetricsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailTemplates emailTemplates;

    @Autowired
    private SessionData sessionData;

    private int pageVisits = 0;

    @GetMapping("/login")
    public String handleRequest(Model model, @Param("status") String status, @Param("previous") String previous,
                                HttpSession session) throws IOException, MessagingException {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        System.out.println("User : " + sessionData.getUser());

        if (previous!=null)
            session.setAttribute("previous", previous);

        if (status!=null && status.equals("wrongCredentials")) {
            model.addAttribute("status", "wrongCredentials");
        }
        else if (status!=null && status.equals("success")){
            model.addAttribute("status", "success");

            if (session.getAttribute("previous")!=null) {
                String previousPage = (String) session.getAttribute("previous");
                session.removeAttribute("previous");
                return "redirect:/" +previousPage;
            }
        }
        else if (sessionData.getUser()!=null) {
            model.addAttribute("status", "alreadyLoggedIn");
        }

        model.addAttribute("formLogin", new FormLogin());
        return "login";
    }

    @GetMapping("/do-logout")
    public String handleRequest2(Model model, @Param("status") String status) throws IOException, MessagingException {

        System.out.println("User : " + sessionData.getUser());

        if (status!=null && status.equals("logoutSucceeded")) {
            model.addAttribute("status", "logoutSucceeded");
            sessionData.setUser(null);
        }

        else if (sessionData.getUser()==null) {
            model.addAttribute("status", "alreadyLoggedIn");
        }

        return "logout";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        model.addAttribute("formRegister", new FormRegister());
        return "register";
    }

    @PostMapping("/register")
    public String handleRequest(Model model, @Valid @ModelAttribute("formRegister") FormRegister formRegister,
                                BindingResult bindingResult) throws IOException, MessagingException {

        visitsMetricsService.increaseCounters(model, ++pageVisits);

        if (!bindingResult.hasErrors()) {
            model.addAttribute("status", "dataValidated");
            User user = new User(null,
                    formRegister.getUsername(),
                    formRegister.getPassword(),
                    formRegister.getFullname(),
                    formRegister.getEmail(),
                    formRegister.getTel(),
                    String.valueOf(new Random().nextInt(10000)),
                    null,
                    null
            );
            emailTemplates.sendEmailCompleteRegister(user);
            authService.registerUser(user);
        }

        //authService.registerUser(user);
        System.out.println(bindingResult);
        return "register";
    }

    @GetMapping("/register/{code}")
    public String handleRequest3(Model model, @PathVariable String code) {
        User user = userRepository.findUserByStatus(code);

        if (user==null)
            model.addAttribute("status", "verifyFailed");
        else {
            model.addAttribute("status", "verifySucceeded");
            user.setStatus("verified");
            userRepository.save(user);
        }

        return "register";
    }

    @GetMapping("/password-reset")
    public String showPasswordResetForm(Model model) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        model.addAttribute("formPasswordReset", new FormPasswordReset());
        return "password-reset";
    }

    @PostMapping("/password-reset")
    public String handleRequest2(Model model, @Valid @ModelAttribute("formPasswordReset") FormPasswordReset formPasswordReset,
                                BindingResult bindingResult) throws IOException, MessagingException {

        visitsMetricsService.increaseCounters(model, ++pageVisits);

        if (bindingResult.hasErrors()) {
            model.addAttribute("status", "emailNotValid");
            return "password-reset";
        }
        else{
            model.addAttribute("status", "emailValid");
            Random r = new Random();
            String code = String.valueOf(r.nextInt(1000, 2000));
            User user = userRepository.findUserByEmail(formPasswordReset.getEmail());
            user.setCode(code);
            userRepository.save(user);

            emailTemplates.sendEmailPasswordReset(formPasswordReset.getEmail(), code);
            return "password-reset";
        }
    }

    @GetMapping("/password-reset/{code}")
    public String showPasswordReset2(Model model, @PathVariable String code) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        User user = userRepository.findUserByCode(code);
        if (user!=null && code.equals(user.getCode())) {
            FormPasswordReset2 formPasswordReset2 = new FormPasswordReset2();
            formPasswordReset2.setCode(code);
            model.addAttribute("formPasswordReset2", formPasswordReset2);
            return "password-reset2";
        }
        else {
            return "error";
        }
    }

    @PostMapping("/password-reset2")
    public String handleRequest3(Model model, @Valid @ModelAttribute("formPasswordReset2") FormPasswordReset2 formPasswordReset2,
                                 BindingResult bindingResult) throws IOException, MessagingException {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        if (!bindingResult.hasErrors()) {
            User oldUser = userRepository.findUserByCode(formPasswordReset2.getCode());
            User user = new User(null,
                    oldUser.getUsername(),
                    formPasswordReset2.getPassword(),
                    oldUser.getFullname(),
                    oldUser.getEmail(),
                    oldUser.getTel(),
                    "verified",
                    null, null);

            authService.registerUser(user);
            model.addAttribute("status", "changeDone");
            return "password-reset2";
        }
        else {
            model.addAttribute("formPasswordReset2", formPasswordReset2);
            return "password-reset2";
        }
    }

}