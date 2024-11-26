package com.prixbanque.accounts_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    private Double balance;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;
}
