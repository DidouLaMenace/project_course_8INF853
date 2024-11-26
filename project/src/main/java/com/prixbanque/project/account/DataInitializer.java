package com.prixbanque.project.account;

import com.prixbanque.project.account.model.Account;
import com.prixbanque.project.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        // Supprime les données existantes
        accountRepository.deleteAll();

        // Ajoute des comptes par défaut avec email et mot de passe
        Account account1 = new Account(
                null, "123456789", 1000.0, "user1@example.com", "password123"
        );
        Account account2 = new Account(
                null, "987654321", 500.0, "user2@example.com", "password456"
        );

        accountRepository.save(account1);
        accountRepository.save(account2);

        System.out.println("Accounts initialized in account_db");
    }
}
