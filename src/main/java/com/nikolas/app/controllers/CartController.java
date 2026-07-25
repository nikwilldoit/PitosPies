//package com.nikolas.app.controllers;
//
//import com.nikolas.app.beans.SessionBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//
//    @Autowired
//    private SessionBean sessionBean;
//
//
//    @GetMapping("/set/{product}/{quantity}")
//    public String handleRequest(Model model, @PathVariable String product, @PathVariable int quantity) {
//        sessionBean.getCart().put(product, quantity);
//        model.addAttribute("product", product);
//        model.addAttribute("quantity", quantity);
//        return "set";
//    }
//
//    @GetMapping("/remove/{product}")
//    public String handleRequest2(Model model, @PathVariable String product) {
//        if (sessionBean.getCart().remove(product)!=null)
//            model.addAttribute("product", product);
//        else
//            model.addAttribute("failed", true);
//        return "remove";
//    }
//
//    @GetMapping("/show")
//    public String handleRequest3() {
//        return "show";
//    }
//}
