package com.prixbanque.accounts_ms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private Long userId; // de la table 'user' dans la BDD du auth-ms

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
