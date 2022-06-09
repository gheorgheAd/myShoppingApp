package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User findById(Long id) throws NoUserFoundException;

    void deleteById(Long id) throws NoUserFoundException;

    List<User> findAll();
}
