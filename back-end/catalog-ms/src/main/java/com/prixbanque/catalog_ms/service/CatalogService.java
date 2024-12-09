package com.prixbanque.catalog_ms.service;

import com.prixbanque.catalog_ms.model.Event;
import com.prixbanque.catalog_ms.model.EventCategory;
import com.prixbanque.catalog_ms.repository.EventCategoryRepository;
import com.prixbanque.catalog_ms.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventCategoryRepository eventCategoryRepository;

    public Page<Event> getIncomingEvents(Pageable pageable, Long categoryId, String searchText) {
        LocalDateTime currentTime = LocalDateTime.now();
        return eventRepository.findAllFiltered(categoryId, searchText, currentTime, pageable);
    }

    public List<EventCategory> getAllEventCategories() {
        return eventCategoryRepository.findAll();
    }

    public Optional<Event> getEventById(long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        event.setEventId(null);
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }
}
