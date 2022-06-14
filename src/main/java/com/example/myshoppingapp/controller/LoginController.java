package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
    @GetMapping("/register")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("newUser", new User());
        return "user-register-form";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "successful-registration-message";
    }
}
