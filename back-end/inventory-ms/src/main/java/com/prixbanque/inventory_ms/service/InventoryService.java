package com.prixbanque.inventory_ms.service;

import com.prixbanque.inventory_ms.model.Inventory;
import com.prixbanque.inventory_ms.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Récupérer toutes les réservations
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    // Récupérer une réservation par ID
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory non trouvée pour l'ID : " + id));
    }

    // Créer une nouvelle réservation
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Supprimer une réservation par ID
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new RuntimeException("Inventory non trouvée pour l'ID : " + id);
        }
        inventoryRepository.deleteById(id);
    }

    // Récupérer les réservations par userId
    public List<Inventory> getInventoriesByUserId(Long userId) {
        return inventoryRepository.findByUserId(userId);
    }

}
