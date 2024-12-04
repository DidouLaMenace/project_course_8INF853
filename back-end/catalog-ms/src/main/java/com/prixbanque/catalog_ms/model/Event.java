package com.prixbanque.catalog_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    private String description;

    @NotBlank
    private String location;

    @NotNull
    private Double ticketPrice;

    @NotNull
    private Integer ticketNumber;

    private Long categoryId;

    private String imageUrls;
}
