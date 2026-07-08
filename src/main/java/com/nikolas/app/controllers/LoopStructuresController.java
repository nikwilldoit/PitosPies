package com.nikolas.app.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoopStructuresController {

    @GetMapping("loop-structures")
    public String handleRequest(Model model) {
        int[] array = {1,2,3};
        List<Integer> list = List.of(1,2,3);
        Map<String,Integer> map = Map.of("key1",1,"key2",2,"key3",3);

        model.addAttribute("array",array);
        model.addAttribute("list",list);
        model.addAttribute("map",map);

        return "loop-structures";
    }
}
