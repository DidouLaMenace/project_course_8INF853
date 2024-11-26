package com.prixbanque.project.transactions.controller;

import com.prixbanque.project.transactions.dto.TransactionDTO;
import com.prixbanque.project.transactions.model.Transaction;
import com.prixbanque.project.transactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Créer une transaction (virement)
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionService.processTransaction(transactionDTO);
        return ResponseEntity.ok(transaction);
    }

    // Obtenir toutes les transactions envoyées par un compte spécifique
    @GetMapping("/sender/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsBySender(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsBySender(accountNumber));
    }

    // Obtenir toutes les transactions reçues par un compte spécifique
    @GetMapping("/recipient/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByRecipient(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsByRecipient(accountNumber));
    }
}
