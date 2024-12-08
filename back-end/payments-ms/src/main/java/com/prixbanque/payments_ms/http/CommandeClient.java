package com.prixbanque.booking_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "commande", path = "/commande")
public interface CommandeClient {

    @PostMapping("/create")
    ResponseEntity<String> createCommande(@RequestBody Object commandeDetails);
}
