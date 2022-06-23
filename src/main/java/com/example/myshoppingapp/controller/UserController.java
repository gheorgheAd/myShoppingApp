package com.example.myshoppingapp.controller;

import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String addUser(ModelMap modelMap) {
        User user = new User();
        modelMap.addAttribute("user", user);
        return "user-register-form";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        userService.save(user);
        return successfulRegistration();
    }

    @GetMapping("/register/successful-registration-message")
    public String successfulRegistration() {
        return "successful-registration-message";
    }

    @GetMapping("/message-sent")
    public String successfulSend() {
        return "message-sent";
    }

    @PostMapping("/profile/update")
    public String updateUser(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String editUser(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        modelMap.addAttribute(user);
        return "profile";
    }

}
