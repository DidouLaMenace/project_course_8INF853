package com.prixbanque.project.transactions;

import com.prixbanque.project.transactions.model.Transaction;
import com.prixbanque.project.transactions.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        // Supprime les données existantes
        transactionRepository.deleteAll();

        // Ajoute des transactions par défaut
        Transaction transaction1 = new Transaction(null, "123456789", "987654321", 100.0, LocalDateTime.now(), "SUCCESS");
        Transaction transaction2 = new Transaction(null, "987654321", "123456789", 50.0, LocalDateTime.now(), "SUCCESS");

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        System.out.println("Transactions initialized in transaction_db");
    }
}
