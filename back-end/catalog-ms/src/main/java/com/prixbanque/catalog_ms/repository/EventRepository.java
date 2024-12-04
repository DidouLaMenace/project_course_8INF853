package com.prixbanque.catalog_ms.repository;

import com.prixbanque.catalog_ms.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCategoryId(long categoryId);

    List<Event> findByDateTimeAfter(LocalDateTime date);

    List<Event> findByCategoryIdAndDateTimeAfter(Long categoryId, LocalDateTime date);
}
