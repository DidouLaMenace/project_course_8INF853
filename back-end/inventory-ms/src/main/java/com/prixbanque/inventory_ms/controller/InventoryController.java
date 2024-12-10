package com.prixbanque.inventory_ms.controller;

import com.prixbanque.inventory_ms.dto.InventoryDTO;
import com.prixbanque.inventory_ms.model.Inventory;
import com.prixbanque.inventory_ms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint pour ajouter une réservation
    @PostMapping("/reservations")
    public ResponseEntity<String> saveReservation(@RequestParam Long userId, @RequestParam Long eventId) {
        System.out.println("Début de saveReservation. userId: " + userId + ", eventId: " + eventId);
        inventoryService.saveReservation(userId, eventId);
        return ResponseEntity.ok("Réservation enregistrée avec succès.");
    }

    // Endpoint pour récupérer toutes les réservations d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InventoryDTO>> getUserReservations(@PathVariable Long userId) {
        try {
            List<Inventory> reservations = inventoryService.getReservationsByUserId(userId);
            List<InventoryDTO> reservationDTOs = reservations.stream()
                    .map(reservation -> new InventoryDTO(reservation.getId(), reservation.getUserId(), reservation.getEventId()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(reservationDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
