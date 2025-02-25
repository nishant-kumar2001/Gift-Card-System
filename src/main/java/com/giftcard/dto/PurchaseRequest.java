package com.giftcard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private UUID userId;
    private UUID giftCardId;
    private BigDecimal balance;
    // Getters/setters
}