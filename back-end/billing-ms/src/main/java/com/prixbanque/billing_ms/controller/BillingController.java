package com.prixbanque.billing_ms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BillingController {

    @GetMapping("/ping")
    public String ping() {
        return "Billing microservice is alive!";
    }
}
