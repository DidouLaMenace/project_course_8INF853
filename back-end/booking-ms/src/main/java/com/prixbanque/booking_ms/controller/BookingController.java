package com.prixbanque.booking_ms.controller;

import com.prixbanque.booking_ms.model.Cart;
import com.prixbanque.booking_ms.service.BookingService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/cart")
    public ResponseEntity<Cart> addToCart(
            @RequestParam @NotNull Long userId,
            @RequestParam @NotNull Long eventId,
            @RequestParam @NotNull Double totalAmount) {
        Cart cart = bookingService.addToCart(userId, eventId, totalAmount);
        return ResponseEntity.ok(cart);
    }

}
