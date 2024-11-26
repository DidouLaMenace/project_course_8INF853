package com.prixbanque.banking_ms.controller;

import com.prixbanque.banking_ms.service.BankAccountsService;
import com.prixbanque.banking_ms.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BankingController {

    @Autowired
    BankAccountsService bankAccountsService;

    @Autowired
    TransactionsService transactionsService;

    @GetMapping("/ping")
    public String ping() {
        return "Banking microservice is alive!";
    }
}
