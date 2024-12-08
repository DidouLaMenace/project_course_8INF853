package com.prixbanque.commande_ms.service;

import com.google.zxing.WriterException;
import com.prixbanque.commande_ms.model.Commande;
import com.prixbanque.commande_ms.model.QRCodeUtils;
import com.prixbanque.commande_ms.model.Ticket;
import com.prixbanque.commande_ms.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;

    public List<Commande> getCommandesByUserId(Long userId) {
        return commandeRepository.findByUserId(userId);
    }

    public Ticket getTicketDetails(Long ticketId) {
        return commandeRepository.findAll().stream()
                .flatMap(commande -> commande.getTickets().stream())
                .filter(ticket -> ticket.getId().equals(ticketId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public byte[] generateTicketQRCode(Ticket ticket) {
        try {
            String qrText = String.format("Event: %s\nDate: %s\n: %s\nPrice: %.2f",
                    ticket.getEventName(), ticket.getEventDate(), ticket.getPrice());
            return QRCodeUtils.generateQRCode(qrText);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}
