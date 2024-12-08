package com.prixbanque.booking_ms.service;

import com.prixbanque.booking_ms.http.CommandeClient;
import com.prixbanque.booking_ms.http.PaymentsClient;
import com.prixbanque.booking_ms.model.Booking;
import com.prixbanque.booking_ms.model.Cart;
import com.prixbanque.booking_ms.repository.BookingRepository;
import com.prixbanque.booking_ms.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final CartRepository cartRepository;
    private final BookingRepository bookingRepository;
    private final PaymentsClient paymentsClient;
    private final CommandeClient commandeClient;

    public Cart addToCart(Long userId, Long eventId, Double totalAmount) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setEventId(eventId);
        cart.setTotalAmount(BigDecimal.valueOf(totalAmount)); // Conversion Double -> BigDecimal
        return cartRepository.save(cart);
    }


    public Booking confirmBooking(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        // Conversion BigDecimal vers Double
        Double totalAmount = cart.getTotalAmount().doubleValue();

        boolean paymentStatus = paymentsClient.processPayment(cartId, totalAmount).getBody();
        if (!paymentStatus) {
            throw new RuntimeException("Payment failed");
        }

        Booking booking = new Booking();
        booking.setUserId(cart.getUserId());
        booking.setEventId(cart.getEventId());
        booking.setTotalAmount(cart.getTotalAmount());
        bookingRepository.save(booking);

        // Appeler la méthode createOrder avec deux arguments
        commandeClient.createOrder(cart.getUserId(), cart.getEventId());

        return booking;
    }
}
