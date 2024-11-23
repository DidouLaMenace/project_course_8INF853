package com.prixbanque.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @NotNull
    @Positive
    private String accountNumber;

    @NotBlank
    private Double balance;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String password;
}
