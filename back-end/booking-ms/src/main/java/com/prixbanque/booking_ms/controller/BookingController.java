package com.prixbanque.booking_ms.controller;

import com.prixbanque.booking_ms.model.Booking;
import com.prixbanque.booking_ms.model.Cart;
import com.prixbanque.booking_ms.service.BookingService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingService bookingService;

    @GetMapping("/ping")
    public String ping() {
        return "Booking microservice is alive!";
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> addToCart(
            @RequestParam @NotNull Long userId,
            @RequestParam @NotNull Long eventId,
            @RequestParam @NotEmpty String seats) {
        Cart cart = bookingService.addToCart(userId, eventId, seats);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/confirm/{cartId}")
    public ResponseEntity<Booking> confirmBooking(@PathVariable @NotNull Long cartId) {
        Booking booking = bookingService.confirmBooking(cartId);
        return ResponseEntity.ok(booking);
    }
}
