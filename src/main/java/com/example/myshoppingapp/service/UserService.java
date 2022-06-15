package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    User saveUser(User user);
}
