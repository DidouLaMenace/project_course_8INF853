package com.prixbanque.payments_ms.controller;

import com.prixbanque.payments_ms.service.PaymentsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @PostMapping("/process/{cartId}")
    public ResponseEntity<String> processPayment(@PathVariable @NotNull Long cartId) {
        return paymentsService.processPayment(cartId);
    }
}
