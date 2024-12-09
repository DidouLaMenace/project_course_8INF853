package com.prixbanque.payments_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@FeignClient(name = "banking", url = "http://localhost:8083") // Remplacez par l'URL réelle du service bancaire
public interface BankingClient {
    @GetMapping("/accounts/balance")
    ResponseEntity<BigDecimal> getAccountBalance();
}
