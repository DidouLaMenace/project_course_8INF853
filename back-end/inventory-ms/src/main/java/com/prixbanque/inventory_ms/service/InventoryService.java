package com.prixbanque.inventory_ms.service;

import com.prixbanque.inventory_ms.dto.InventoryDTO;
import com.prixbanque.inventory_ms.model.Inventory;
import com.prixbanque.inventory_ms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory saveReservation(Long userId, Long eventId) {
        // Créer une nouvelle instance de l'entité Inventory
        Inventory inventory = new Inventory();
        inventory.setUserId(userId);
        inventory.setEventId(eventId);

        // Enregistrer la réservation dans la base de données via le repository
        Inventory savedReservation = inventoryRepository.save(inventory);

        // Retourner la réservation sauvegardée
        return savedReservation;
    }


    // Récupérer toutes les réservations pour un utilisateur
    public List<Inventory> getReservationsByUserId(Long userId) {
        return inventoryRepository.findByUserId(userId);
    }
}
