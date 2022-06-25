package com.example.myshoppingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    @GetMapping("/payment")
    public String payment() {
        return "payment";
    }

    @GetMapping("/order-details")
    public String orderDetails() {
        return "order-details";
    }

    @GetMapping("/recipes")
    public String recipes() {
        return "recipes";
    }

    @GetMapping("/french-cafe")
    public String frenchCafe() {
        return "french-cafe";
    }

    @GetMapping("/pour-over-coffee")
    public String pourOverCoffee() {
        return "pour-over-coffee";
    }

    @GetMapping("/cafecito")
    public String cafecito() {
        return "cafecito";
    }
}
