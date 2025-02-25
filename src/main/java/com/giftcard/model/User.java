package com.giftcard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import com.giftcard.config.Role;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    // User.java
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Change from String to Role enum

//    @Column(nullable = false)
    private BigDecimal balance;  // User wallet balance
}
