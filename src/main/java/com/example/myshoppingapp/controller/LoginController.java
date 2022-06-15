package com.example.myshoppingapp.controller;


import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    UserServiceImpl userServiceImpl;

    public LoginController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
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
        userServiceImpl.saveUser(user);
        return "redirect:/successful-registration-message";
    }
}
