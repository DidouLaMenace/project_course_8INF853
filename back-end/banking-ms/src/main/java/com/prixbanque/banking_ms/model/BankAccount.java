package com.prixbanque.banking_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    @Id
    private String bankAccountNumber;

    // Generates a random account number
    @PrePersist
    public void generateBankAccountNumber() {
        if (this.bankAccountNumber == null) {
            Random random = new Random();
            StringBuilder idBuilder = new StringBuilder();

            for (int i = 0; i < 12; i++) {
                idBuilder.append(random.nextInt(10)); // Generate random digit (0-9)
            }

            bankAccountNumber = idBuilder.toString();
        }
    }

    @Column(nullable = false)
    private Long ownerUserId;

    @Column(nullable = false)
    private Double balance;
}
