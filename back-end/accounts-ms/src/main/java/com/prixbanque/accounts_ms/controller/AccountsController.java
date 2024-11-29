package com.prixbanque.accounts_ms.controller;

import com.prixbanque.accounts_ms.dto.AccountDTO;
import com.prixbanque.accounts_ms.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.prixbanque.accounts_ms.service.AccountService;

@RestController
@RequestMapping
public class AccountsController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/ping")
    public String ping() {
        return "Account microservice is alive!";
    }

    @PostMapping("/createAccount")
    public AccountDTO createAccount(String email, String password, String firstName, String lastName) {
        Account newAccount = accountService.createAccount(email, password, firstName, lastName);

        return modelMapper.map(newAccount, AccountDTO.class);
    }
}
