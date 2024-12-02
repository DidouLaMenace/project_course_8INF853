package com.prixbanque.accounts_ms.controller;

import com.prixbanque.accounts_ms.dto.AccountDTO;
import com.prixbanque.accounts_ms.http.AuthClient;
import com.prixbanque.accounts_ms.model.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prixbanque.accounts_ms.service.AccountService;

@RestController
@RequestMapping
public class AccountsController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthClient authClient;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Account microservice is alive!");
    }

    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestParam String email, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        Account newAccount = accountService.createAccount(email, password, firstName, lastName);
        AccountDTO dto = modelMapper.map(newAccount, AccountDTO.class);
        dto.setEmail(authClient.getEmail(dto.getUserId()).getBody());
        return ResponseEntity.ok(dto);
    }
}
