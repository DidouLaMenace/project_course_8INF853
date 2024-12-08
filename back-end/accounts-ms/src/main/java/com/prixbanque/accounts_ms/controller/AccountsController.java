package com.prixbanque.accounts_ms.controller;

import com.prixbanque.accounts_ms.dto.AccountDTO;
import com.prixbanque.accounts_ms.model.Account;
import com.prixbanque.accounts_ms.service.SessionService;
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
    private SessionService sessionService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Account microservice is alive!");
    }

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestParam String email, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName) {
        Account newAccount = accountService.register(email, password, firstName, lastName);
        AccountDTO dto = modelMapper.map(newAccount, AccountDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try {
            String sessionId = accountService.login(email, password);
            return ResponseEntity.ok(sessionId);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Session-Id") String sessionId) {
        sessionService.logout(sessionId);
        return ResponseEntity.noContent().build();
    }

    // valide la session et renvoie le userId de l'utilisateur connecté si la session est valide
    @GetMapping("/session/validate")
    public ResponseEntity<Long> validateSession(@RequestParam String sessionId) {
        if (sessionService.validateSession(sessionId)) {
            return ResponseEntity.ok(sessionService.getUserIdBySessionId(sessionId).get());
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AccountDTO> getAccountById(@RequestHeader("Session-Id") String sessionId, @PathVariable Long userId) {
        if (!sessionService.validateSession(sessionId)) {
            return ResponseEntity.status(401).body(null);
        }

        Account account = accountService.getAccountById(userId);
        if (account == null) return ResponseEntity.status(404).body(null);
        else return ResponseEntity.ok(modelMapper.map(account, AccountDTO.class));
    }
}
