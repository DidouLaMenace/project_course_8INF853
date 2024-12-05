package com.prixbanque.commande_ms.repository;

import com.prixbanque.commande_ms.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByUserId(Long userId);
}
