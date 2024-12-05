package com.prixbanque.commande_ms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String eventDate;
    private String seatNumber;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande; // La commande associée

    @Lob
    private byte[] qrCode; // QR code encodé en image (PNG)
}
