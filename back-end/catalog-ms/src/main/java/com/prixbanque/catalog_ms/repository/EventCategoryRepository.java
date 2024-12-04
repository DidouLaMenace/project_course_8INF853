package com.prixbanque.catalog_ms.repository;

import com.prixbanque.catalog_ms.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
}
