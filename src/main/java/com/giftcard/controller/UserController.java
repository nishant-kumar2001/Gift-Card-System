package com.giftcard.controller;

import com.giftcard.config.Role;
import com.giftcard.dto.AuthRequest;
import com.giftcard.dto.UserRegistrationRequest;
import com.giftcard.dto.UserResponse;
import com.giftcard.model.User;
import com.giftcard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/register")
//    public ResponseEntity<UserResponse> registerUser(@RequestBody User user) {
//        User savedUser = userService.registerUser(user);
//        UserResponse response = mapToResponse(savedUser);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest registrationRequest // Use DTO here
    ) {
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setRole(Role.valueOf(registrationRequest.getRole().toUpperCase())); // Convert String to Role enum
        user.setBalance(BigDecimal.ZERO);

        User savedUser = userService.registerUser(user);
        UserResponse response = mapToResponse(savedUser);
        return ResponseEntity.ok(response);
    }

    // Add this method inside the controller class
    private UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setBalance(user.getBalance());
        return response;
    }
//    public ResponseEntity<User> registerUser(@RequestBody User user) {
//        return ResponseEntity.ok(userService.registerUser(user));
//    }



    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
