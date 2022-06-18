package com.example.myshoppingapp.service;

import com.example.myshoppingapp.exception.NoUserFoundException;
import com.example.myshoppingapp.model.User;
import com.example.myshoppingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        if(!user.getPassword().startsWith("$2a$12")) {
            user.setPassword(encoder.encode(user.getPassword()));
        }

        if(user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        if(!user.isEnabled()) {
            user.setEnabled(true);
        }

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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
