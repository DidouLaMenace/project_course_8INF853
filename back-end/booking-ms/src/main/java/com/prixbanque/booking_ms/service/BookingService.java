package com.prixbanque.booking_ms.service;

import com.prixbanque.booking_ms.model.Booking;
import com.prixbanque.booking_ms.model.Cart;
import com.prixbanque.booking_ms.model.Status;
import com.prixbanque.booking_ms.repository.BookingRepository;
import com.prixbanque.booking_ms.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CartRepository cartRepository;

    @Transactional
    public Cart addToCart(Long userId, Long eventId, String seats) {
        Cart cart = cartRepository.findByUserIdAndEventId(userId, eventId)
                .orElse(Cart.builder()
                        .userId(userId)
                        .eventId(eventId)
                        .seats(seats)
                        .status(Status.PENDING)
                        .expiresAt(LocalDateTime.now().plusMinutes(15))
                        .build());

        return cartRepository.save(cart);
    }

    @Transactional
    public Booking confirmBooking(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Booking booking = Booking.builder()
                .userId(cart.getUserId())
                .eventId(cart.getEventId())
                .seats(cart.getSeats())
                .status(Status.CONFIRMED)
                .createdAt(LocalDateTime.now())
                .build();

        cartRepository.delete(cart);
        return bookingRepository.save(booking);
    }
}
