package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;

public interface UserService {
    User save(User user);

    User findById(Long id) throws NoUserFoundException;
}
