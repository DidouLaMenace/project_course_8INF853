package com.prixbanque.commande_ms.controller;

import com.prixbanque.commande_ms.model.Commande;
import com.prixbanque.commande_ms.model.Ticket;
import com.prixbanque.commande_ms.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping("/ping")
    public String ping() {
        return "Commande microservice is alive!";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Commande>> getCommandesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(commandeService.getCommandesByUserId(userId));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> getTicketDetails(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commandeService.getTicketDetails(ticketId));
    }

    @GetMapping("/ticket/{ticketId}/qrcode")
    public ResponseEntity<byte[]> getTicketQRCode(@PathVariable Long ticketId) {
        Ticket ticket = commandeService.getTicketDetails(ticketId);
        byte[] qrCode = commandeService.generateTicketQRCode(ticket);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qrcode.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }
}
