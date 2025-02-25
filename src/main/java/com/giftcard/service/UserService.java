package com.giftcard.service;

import com.giftcard.config.Role;
import com.giftcard.dto.UserRegistrationRequest;
import com.giftcard.dto.UserResponse;
import com.giftcard.exception.EmailAlreadyExistsException;
import com.giftcard.model.User;
import com.giftcard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import java.util.Optional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // UserService.java
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User  registrationRequest) {

        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole(Role.valueOf(String.valueOf(registrationRequest.getRole()))); // Ensure uppercase
        user.setBalance(BigDecimal.ZERO);
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
