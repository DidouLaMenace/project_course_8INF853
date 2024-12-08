package com.prixbanque.booking_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "commande", url = "http://localhost:8083/commande")
public interface CommandeClient {

    @PostMapping("/create")
    ResponseEntity<Void> createOrder(@RequestParam Long userId, @RequestParam Long eventId);
}
