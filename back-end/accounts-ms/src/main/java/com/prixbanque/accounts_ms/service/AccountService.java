package com.prixbanque.accounts_ms.service;

import com.prixbanque.accounts_ms.dto.AccountDTO;
import com.prixbanque.accounts_ms.exception.AccountNotFoundException;
import com.prixbanque.accounts_ms.http.AuthClient;
import com.prixbanque.accounts_ms.http.BankingClient;
import com.prixbanque.accounts_ms.model.Account;
import com.prixbanque.accounts_ms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthClient authClient;
    @Autowired
    private BankingClient bankingClient;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found for ID: " + id));
    }

    public Account createAccount(String email, String password, String firstName, String lastName) {
        // Enregistre les identifiants dans le auth-ms
        Long userId = authClient.register(email, password).getBody();
        // Créé un compte bancaire dans le banking-ms
        bankingClient.createBankAccount(userId).getBody();

        Account account = new Account(userId, firstName, lastName);
        return accountRepository.save(account);
    }

}
