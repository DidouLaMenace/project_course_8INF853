package com.prixbanque.payments_ms.model;

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

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 19)
    private String number;

    @NotBlank
    @Size(max = 7)
    private String expiration;

    @NotBlank
    @Size(max = 3, min = 3)
    private String code;

    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private Long orderId;

    // Nouvelle propriété cartId
    @NotNull
    private Long cartId; // Correspond à l'ID du panier lié au paiement
}
