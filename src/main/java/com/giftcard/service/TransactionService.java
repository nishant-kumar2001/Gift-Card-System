package com.giftcard.service;

import com.giftcard.model.Transaction;
import com.giftcard.model.User;
import com.giftcard.model.GiftCard;
import com.giftcard.repository.TransactionRepository;
import com.giftcard.repository.UserRepository;
import com.giftcard.repository.GiftCardRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final GiftCardRepository giftCardRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              GiftCardRepository giftCardRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.giftCardRepository = giftCardRepository;
    }

    @Transactional
    public Transaction purchaseGiftCard(UUID userId, UUID giftCardId, BigDecimal amount) throws Exception {



        Optional<User> userOpt = userRepository.findById(userId);
//        System.out.println("userOpt:"+ userOpt);
        Optional<GiftCard> giftCardOpt = giftCardRepository.findById(giftCardId);
//        System.out.println("giftCardOpt:"+ giftCardOpt);

        if (userOpt.isEmpty() || giftCardOpt.isEmpty()) {
            throw new Exception("User or Gift Card not found");
        }

        User user = userOpt.get();
        System.out.println("user :"+user.getBalance());
        GiftCard giftCard = giftCardOpt.get();
        System.out.println("giftCard :"+giftCard.getBalance());


        // TransactionService.java (purchaseGiftCard method)
        giftCard.setUser(user); // Assign ownership
        giftCardRepository.save(giftCard);


        if (user.getBalance().compareTo(amount) < 0) {
            throw new Exception("Insufficient balance");
        }

        user.setBalance(user.getBalance().subtract(amount));
        userRepository.save(user);

        // After deducting user balance:
        giftCard.setBalance(giftCard.getBalance().add(amount)); // Assuming gift cards are topped up
        giftCardRepository.save(giftCard);

        Transaction transaction = Transaction.builder()
                .user(user)
                .giftCard(giftCard)
                .balance(amount)
                .transactionDate(LocalDateTime.now())
                .status("SUCCESS")
                .build();

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(UUID userId) {
        return transactionRepository.findByUserId(userId);
    }
}
