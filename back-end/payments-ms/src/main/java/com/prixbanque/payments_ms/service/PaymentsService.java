package com.prixbanque.payments_ms.service;

import com.prixbanque.payments_ms.http.BookingClient;
import com.prixbanque.payments_ms.http.BankingClient;
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

    private final BankingClient bankClient; // Client pour interroger le service bancaire

    public ResponseEntity<String> processPayment(BigDecimal amount) {
        // Appel au service bancaire pour récupérer le solde
        ResponseEntity<BigDecimal> bankResponse = bankClient.getAccountBalance();
        if (bankResponse.getBody() == null) {
            return ResponseEntity.badRequest().body("Impossible de récupérer le solde.");
        }

        BigDecimal accountBalance = bankResponse.getBody();

        // Vérification du solde
        if (accountBalance.compareTo(amount) < 0) {
            return ResponseEntity.badRequest().body("Solde insuffisant pour effectuer le paiement.");
        }

        // Si le solde est suffisant
        return ResponseEntity.ok("Solde suffisant. Paiement possible.");
    }
}


//@Service
//@RequiredArgsConstructor
//public class PaymentsService {
//
//    private final BookingClient bookingClient;
//    private final PaymentsRepository paymentsRepository;
//
//    public ResponseEntity<String> processPayment(Long cartId) {
//        // Appel au client Booking pour récupérer le montant total du panier
//        ResponseEntity<Double> cartResponse = bookingClient.getCartTotal(cartId);
//
//        if (cartResponse.getBody() == null) {
//            throw new IllegalArgumentException("Le montant total du panier est null");
//        }
//
//        // Conversion de Double en BigDecimal
//        BigDecimal totalAmount = BigDecimal.valueOf(cartResponse.getBody());
//
//        // Logique de paiement ici (par exemple, interaction avec un système de paiement)
//
//        // Sauvegarde du paiement dans la base de données
//        Payments payment = new Payments();
//        payment.setCartId(cartId);
//        payment.setAmount(totalAmount);
//        payment.setStatus(Status.CONFIRMED);
//        paymentsRepository.save(payment);
//
//        return ResponseEntity.ok("Paiement effectué avec succès");
//    }
//}