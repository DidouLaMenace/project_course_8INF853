package com.prixbanque.banking_ms.service;

import com.prixbanque.banking_ms.dto.TransactionDTO;
import com.prixbanque.banking_ms.model.BankAccount;
import com.prixbanque.banking_ms.model.Transaction;
import com.prixbanque.banking_ms.model.TransactionStatus;
import com.prixbanque.banking_ms.repository.BankAccountRepository;
import com.prixbanque.banking_ms.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Transactional
    public Transaction processTransaction(TransactionDTO transactionDTO) {
        BankAccount senderAccount = bankAccountRepository.findById(transactionDTO.getSenderAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        BankAccount recipientAccount = bankAccountRepository.findById(transactionDTO.getRecipientAccountNumber())
                .orElseThrow(() -> new RuntimeException("Recipient account not found"));

        // Vérification du solde du compte expéditeur
        if (senderAccount.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Insufficient funds in sender's account");
        }

        // Mise à jour des soldes
        senderAccount.setBalance(senderAccount.getBalance() - transactionDTO.getAmount());
        recipientAccount.setBalance(recipientAccount.getBalance() + transactionDTO.getAmount());

        // Sauvegarde des comptes mis à jour
        bankAccountRepository.save(senderAccount);
        bankAccountRepository.save(recipientAccount);

        // Enregistrement de la transaction
        Transaction transaction = new Transaction();
        transaction.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
        transaction.setRecipientAccountNumber(transactionDTO.getRecipientAccountNumber());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus(TransactionStatus.SUCCESS);

        return transactionRepository.save(transaction);
    }

    // Récupérer les transactions envoyées par un compte spécifique
    public List<Transaction> getTransactionsBySender(String accountNumber) {
        return transactionRepository.findBySenderAccountNumber(accountNumber);
    }

    // Récupérer les transactions reçues par un compte spécifique
    public List<Transaction> getTransactionsByRecipient(String accountNumber) {
        return transactionRepository.findByRecipientAccountNumber(accountNumber);
    }
}
