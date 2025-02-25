package com.giftcard.controller;

import com.giftcard.dto.PurchaseRequest;
import com.giftcard.model.Transaction;
import com.giftcard.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<Transaction> purchaseGiftCard(@RequestBody PurchaseRequest request) {
        try {
            System.out.println("Received request: " + request);
            return ResponseEntity.ok(transactionService.purchaseGiftCard(request.getUserId(),
                    request.getGiftCardId(),
                    request.getBalance()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUser(userId));
    }
}
