package com.prixbanque.inventory_ms.model;

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
    private Double price;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory Inventory; // La Inventory associée

    @Lob
    private byte[] qrCode; // QR code encodé en image (PNG)
}
