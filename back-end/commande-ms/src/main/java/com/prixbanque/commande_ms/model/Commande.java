package com.prixbanque.commande_ms.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Identifiant de l'utilisateur ayant passé la commande

    private LocalDateTime purchaseDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private List<Ticket> tickets; // Liste des billets associés à cette commande
}
