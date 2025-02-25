package com.giftcard.service;


import com.giftcard.model.GiftCard;
import com.giftcard.repository.GiftCardRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GiftCardService {

    private final GiftCardRepository giftCardRepository;

    public GiftCardService(GiftCardRepository giftCardRepository) {
        this.giftCardRepository = giftCardRepository;
    }

    public GiftCard createGiftCard(GiftCard giftCard) {
        return giftCardRepository.save(giftCard);
    }

    public List<GiftCard> getAllGiftCards() {
        return giftCardRepository.findAll();
    }

    public Optional<GiftCard> getGiftCardById(UUID id) {
        return giftCardRepository.findById(id);
    }

    public void deleteGiftCard(UUID id) {
        giftCardRepository.deleteById(id);
    }
}
