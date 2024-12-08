package com.prixbanque.payments_ms.service;

import com.prixbanque.payments_ms.http.BookingClient;
import com.prixbanque.payments_ms.model.Payments;
import com.prixbanque.payments_ms.model.Status;
import com.prixbanque.payments_ms.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final BookingClient bookingClient;
    private final PaymentsRepository paymentsRepository;

    public ResponseEntity<String> processPayment(Long cartId) {
        // Appel au client Booking pour récupérer le montant total du panier
        ResponseEntity<Double> cartResponse = bookingClient.getCartTotal(cartId);

        if (cartResponse.getBody() == null) {
            throw new IllegalArgumentException("Le montant total du panier est null");
        }

        // Conversion de Double en BigDecimal
        BigDecimal totalAmount = BigDecimal.valueOf(cartResponse.getBody());

        // Logique de paiement ici (par exemple, interaction avec un système de paiement)

        // Sauvegarde du paiement dans la base de données
        Payments payment = new Payments();
        payment.setCartId(cartId);
        payment.setAmount(totalAmount);
        payment.setStatus(Status.CONFIRMED);
        paymentsRepository.save(payment);

        return ResponseEntity.ok("Paiement effectué avec succès");
    }
}