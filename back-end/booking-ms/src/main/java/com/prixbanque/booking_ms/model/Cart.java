package com.prixbanque.booking_ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// Panier en cours

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;
    private String seats;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime expiresAt;
}
