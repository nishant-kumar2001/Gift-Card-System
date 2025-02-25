package com.giftcard.dto;

import com.giftcard.model.User;
import com.giftcard.config.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String email;
    private String password;
    private Role role;
    private BigDecimal balance;
    // Getters/setters
}
