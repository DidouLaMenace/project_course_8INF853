package com.prixbanque.booking_ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


// Panier finalisé

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;
    private String seats;  // Voir pour plus tard

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}
