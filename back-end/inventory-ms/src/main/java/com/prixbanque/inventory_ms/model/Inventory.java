package com.prixbanque.inventory_ms.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reservation")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    private Long userId; // Identifiant de l'utilisateur ayant passé la Inventory

    @NotBlank
    @Column
    private LocalDateTime EventDate;

    @NotBlank
    @Column
    private String Name;
}
