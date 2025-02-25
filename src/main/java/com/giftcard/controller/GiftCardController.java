package com.giftcard.controller;

import com.giftcard.model.GiftCard;
import com.giftcard.service.GiftCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/giftcards")
public class GiftCardController {

    private final GiftCardService giftCardService;

    public GiftCardController(GiftCardService giftCardService) {
        this.giftCardService = giftCardService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<GiftCard> createGiftCard(@RequestBody GiftCard giftCard) {
        return ResponseEntity.ok(giftCardService.createGiftCard(giftCard));
    }

    @GetMapping("/allgift")
    public ResponseEntity<List<GiftCard>> getAllGiftCards() {
        return ResponseEntity.ok(giftCardService.getAllGiftCards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCard> getGiftCardById(@PathVariable UUID id) {
        Optional<GiftCard> giftCard = giftCardService.getGiftCardById(id);
        return giftCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGiftCard(@PathVariable UUID id) {
        giftCardService.deleteGiftCard(id);
        return ResponseEntity.noContent().build();
    }
}
