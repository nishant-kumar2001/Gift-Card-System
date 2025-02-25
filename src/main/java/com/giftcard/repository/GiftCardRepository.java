package com.giftcard.repository;

import com.giftcard.model.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GiftCardRepository extends JpaRepository<GiftCard, UUID> {
}
