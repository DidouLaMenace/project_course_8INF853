package com.prixbanque.payments_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "booking", path = "/booking")
public interface BookingClient {

    @GetMapping("/cart/{id}/total")
    ResponseEntity<Double> getCartTotal(@PathVariable("id") Long cartId);
}
