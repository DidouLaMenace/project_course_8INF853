package com.prixbanque.accounts_ms.service;

import com.prixbanque.accounts_ms.exception.AccountNotFoundException;
import com.prixbanque.accounts_ms.http.BankingClient;
import com.prixbanque.accounts_ms.model.Account;
import com.prixbanque.accounts_ms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankingClient bankingClient;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + id));
    }

    public Account register(String email, String password, String firstName, String lastName) {
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(passwordEncoder.encode(password));
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setRole("USER");

        // Enregistre le nouvel utilsateur dans la BDD
        Account newAccount = accountRepository.save(account);

        // Créé un compte bancaire dans le banking-ms
        bankingClient.createBankAccount(newAccount.getUserId());

        return newAccount;
    }

    // renvoie l'id de la session ouverte
    public String login(String email, String password) {
        Account user = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // crée une nouvelle session et renvoie l'id
        return sessionService.createSession(user.getUserId());
    }

    public Account getUserAccountById(Long userId) {
        return accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String getFirstNameById(Long userId) {
        Account account = accountRepository.findById(userId)
                .orElseThrow(() -> new AccountNotFoundException("Utilisateur avec l'ID " + userId + " introuvable"));
        return account.getFirstName();
    }

}
