package com.prixbanque.catalog_ms.service;

import com.prixbanque.catalog_ms.model.Event;
import com.prixbanque.catalog_ms.model.EventCategory;
import com.prixbanque.catalog_ms.repository.EventCategoryRepository;
import com.prixbanque.catalog_ms.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CatalogService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    public List<Event> getAllIncomingEvents() {
        LocalDateTime currentTime = LocalDateTime.now();
        return eventRepository.findByDateTimeAfter(currentTime);
    }

    public List<EventCategory> getAllEventCategories() {
        return eventCategoryRepository.findAll();
    }

    public Optional<Event> getEventById(long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCategoryId(long id) {
        LocalDateTime currentTime = LocalDateTime.now();
        return eventRepository.findByCategoryIdAndDateTimeAfter(id, currentTime);
    }

    public Event createEvent(Event event) {
        event.setEventId(null);
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
}
