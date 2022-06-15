package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String addUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "user-register-form";
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        userService.saveUser(user);
        return successfulRegistration();
    }

    @GetMapping("/successful-registration-message")
    public String successfulRegistration() {
        return "successful-registration-message";
    }

}
