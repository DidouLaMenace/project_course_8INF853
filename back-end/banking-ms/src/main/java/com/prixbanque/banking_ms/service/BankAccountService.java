package com.prixbanque.banking_ms.service;

import com.prixbanque.banking_ms.model.BankAccount;
import com.prixbanque.banking_ms.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    /**
     * @param ownerUserId l'id de l'utilisateur associé au compte dans account-ms
     * @return le numéro du compte créé
     */
    public BankAccount createBankAccount(Long userId) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserId(userId);
        bankAccount.setBalance(0d);
        return bankAccountRepository.save(bankAccount);
    }

    public Optional<BankAccount> getBankAccountByBankAccountNumber(String bankAccountNumber) {
        return bankAccountRepository.findById(bankAccountNumber);
    }

    public Double getBalanceByUserId(Long userId) {
        // Récupérer le compte bancaire associé à l'utilisateur
        BankAccount bankAccount = bankAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Compte bancaire non trouvé pour l'utilisateur ID: " + userId));
        return bankAccount.getBalance();
    }

}
