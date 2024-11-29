package com.prixbanque.banking_ms.repository;

import com.prixbanque.banking_ms.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
    List<BankAccount> findByOwnerUserId(Long ownerUserId);
}
