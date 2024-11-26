package com.prixbanque.booking_ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookingController {

    @GetMapping("/ping")
    public String ping() {
        return "Booking microservice is alive!";
    }
}
