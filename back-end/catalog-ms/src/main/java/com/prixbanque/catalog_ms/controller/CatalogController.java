package com.prixbanque.catalog_ms.controller;

import com.prixbanque.catalog_ms.model.Event;
import com.prixbanque.catalog_ms.model.EventCategory;
import com.prixbanque.catalog_ms.service.CatalogService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CatalogController {
    @Autowired
    private CatalogService catalogService;


    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Catalog microservice is alive!");
    }

    // endpoint pour récupérer les events à venir
    // permet de filtrer par catégorie si un categoryId est fourni
    @GetMapping("/events")
    public ResponseEntity<Page<Event>> getEvents(
            @PageableDefault(size=10) Pageable pageable,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String searchText) {

        Page<Event> eventsPage = catalogService.getIncomingEvents(pageable, categoryId, searchText);
        return ResponseEntity.ok(eventsPage);
    }

    // endpoint pour récupérer les catégories
    @GetMapping("/categories")
    public ResponseEntity<List<EventCategory>> getAllCategories() {
        return ResponseEntity.ok(catalogService.getAllEventCategories());
    }

    // endpoint pour récupérer un event par id
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable long id) {
        Optional<Event> event = catalogService.getEventById(id);

        if (event.isPresent())
            return ResponseEntity.ok(event.get());
        else
            return ResponseEntity.notFound().build();
    }

    // endpoint pour ajouter un nouvel event
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(catalogService.createEvent(event));
    }

    // endpoint pour récupérer le nombre de places d'un event
    @GetMapping("/events/{id}/ticketNumber")
    ResponseEntity<Integer> getTicketNumberByEventId(@PathVariable long id) {
        Optional<Event> event = catalogService.getEventById(id);

        if (event.isPresent())
            return ResponseEntity.ok(event.get().getTicketNumber());
        else
            return ResponseEntity.notFound().build();
    }

    // endpoint pour mettre à jour event
    @PutMapping("/events")
    ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        try {
            return ResponseEntity.ok(catalogService.updateEvent(event));
        } catch (OptimisticLockException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
