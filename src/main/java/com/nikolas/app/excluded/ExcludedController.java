package com.nikolas.app.excluded;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExcludedController {

    @GetMapping("/excluded")
    public String handleRequest() {
        System.out.println("Not Working");
        return "excluded";
    }
}
