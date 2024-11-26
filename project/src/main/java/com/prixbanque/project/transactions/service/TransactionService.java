package com.prixbanque.project.transactions.service;

import com.prixbanque.project.transactions.dto.TransactionDTO;
import com.prixbanque.project.account.model.Account;
import com.prixbanque.project.transactions.model.Transaction;
import com.prixbanque.project.account.repository.AccountRepository;
import com.prixbanque.project.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Transaction processTransaction(TransactionDTO transactionDTO) {
        // Récupération des comptes expéditeur et destinataire
        Account senderAccount = accountRepository.findByAccountNumber(transactionDTO.getSenderAccountNumber());
        Account recipientAccount = accountRepository.findByAccountNumber(transactionDTO.getRecipientAccountNumber());

        if (senderAccount == null) {
            throw new RuntimeException("Sender account does not exist: " + transactionDTO.getSenderAccountNumber());
        }

        if (recipientAccount == null) {
            throw new RuntimeException("Recipient account does not exist: " + transactionDTO.getRecipientAccountNumber());
        }

        // Vérification du solde du compte expéditeur
        if (senderAccount.getBalance() < transactionDTO.getAmount()) {
            throw new RuntimeException("Insufficient funds in sender's account");
        }

        // Mise à jour des soldes
        senderAccount.setBalance(senderAccount.getBalance() - transactionDTO.getAmount());
        recipientAccount.setBalance(recipientAccount.getBalance() + transactionDTO.getAmount());

        // Sauvegarde des comptes mis à jour
        accountRepository.save(senderAccount);
        accountRepository.save(recipientAccount);

        // Enregistrement de la transaction
        Transaction transaction = new Transaction();
        transaction.setSenderAccountNumber(transactionDTO.getSenderAccountNumber());
        transaction.setRecipientAccountNumber(transactionDTO.getRecipientAccountNumber());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("SUCCESS");

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
