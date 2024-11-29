package com.prixbanque.banking_ms.controller;

import com.prixbanque.banking_ms.dto.TransactionDTO;
import com.prixbanque.banking_ms.model.Transaction;
import com.prixbanque.banking_ms.service.BankAccountService;
import com.prixbanque.banking_ms.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping
public class BankingController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountService bankAccountService;

    // Créer une transaction (virement)
    @PostMapping("/transactions/create")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.processTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    // Obtenir toutes les transactions envoyées par un compte spécifique
    @GetMapping("/transactions/getBySender/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsBySender(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsBySender(accountNumber));
    }

    // Obtenir toutes les transactions reçues par un compte spécifique
    @GetMapping("/transactions/getByRecipient/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByRecipient(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsByRecipient(accountNumber));
    }

    @PostMapping("/accounts/create")
    public ResponseEntity<String> createBankAccount(@RequestParam Long ownerUserId) {
        return ResponseEntity.ok(bankAccountService.createBankAccount(ownerUserId).getBankAccountNumber());
    }
}
