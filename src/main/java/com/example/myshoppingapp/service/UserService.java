package com.example.myshoppingapp.service;

import com.example.myshoppingapp.model.User;

import java.util.Optional;

public interface UserService {


    Optional<User> findByUsername(String username);
    User saveUser(User user);
  
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        user.setPassword(encoder.encode(user.getPassword()));

        if(user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        if(!user.isEnabled()) {
            user.setEnabled(true);
        }

        return userRepository.save(user);
    }

    public User findById(Long id) throws NoUserFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoUserFoundException("No User Found !");
        }
        return optionalUser.get();
    }

    public void deleteById(Long id) throws NoUserFoundException {
        Optional<User> optionalProduct = userRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NoUserFoundException("Product not found!");
        }
        userRepository.deleteById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
