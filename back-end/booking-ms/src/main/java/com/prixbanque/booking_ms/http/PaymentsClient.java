package com.prixbanque.booking_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payments", url = "http://localhost:8083/payments")
public interface PaymentsClient {

    @PostMapping("/process")
    ResponseEntity<Boolean> processPayment(@RequestParam Long cartId, @RequestParam Double amount);
}
