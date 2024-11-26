package com.prixbanque.project.transactions.repository;

import com.prixbanque.project.transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderAccountNumber(String accountNumber);

    List<Transaction> findByRecipientAccountNumber(String accountNumber);
}
