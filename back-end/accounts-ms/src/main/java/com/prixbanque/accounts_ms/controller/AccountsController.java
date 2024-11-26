package com.prixbanque.accounts_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.prixbanque.accounts_ms.service.AuthService;
import com.prixbanque.accounts_ms.service.UserDataService;

@RestController
@RequestMapping
public class AccountsController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDataService userDataService;

    @GetMapping("/ping")
    public String ping() {
        return "Account microservice is alive!";
    }
}
