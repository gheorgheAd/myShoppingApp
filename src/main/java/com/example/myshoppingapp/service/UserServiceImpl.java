package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findById(Integer id) throws NoUserFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoUserFoundException("No User Found !");
        }
        return optionalUser.get();
    }

    public void deleteById(Integer id) throws NoUserFoundException {
        Optional<User> optionalProduct = userRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoUserFoundException("Product not found!");
        }
        userRepository.deleteById(id);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
