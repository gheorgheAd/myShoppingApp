package com.example.myshoppingapp.service.impl;

import com.example.myshoppingapp.exception.NoProductFoundException;
import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.Product;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.UserRepository;
import com.example.myshoppingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
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

    @Override
    public void deleteById(Long id) throws NoUserFoundException {
        Optional<User> optionalProduct = userRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoUserFoundException("Product not found!");
        }
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
