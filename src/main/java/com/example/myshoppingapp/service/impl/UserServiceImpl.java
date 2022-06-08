package com.example.myshoppingapp.service.impl;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.UserRepository;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) throws NoUserFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoUserFoundException("No User Found !");
        }
        return optionalUser.get();
    }
}
