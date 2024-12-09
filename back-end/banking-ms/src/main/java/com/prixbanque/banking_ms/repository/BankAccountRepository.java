package com.prixbanque.banking_ms.repository;

import com.prixbanque.banking_ms.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    Optional<BankAccount> findByUserId(Long userId);
}
