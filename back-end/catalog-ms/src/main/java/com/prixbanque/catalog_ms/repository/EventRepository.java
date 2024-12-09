package com.prixbanque.catalog_ms.repository;

import com.prixbanque.catalog_ms.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCategoryId(long categoryId);

    List<Event> findByDateTimeAfter(LocalDateTime date);

    List<Event> findByCategoryIdAndDateTimeAfter(Long categoryId, LocalDateTime date);


    @Query("""
                SELECT e 
                FROM Event e 
                WHERE 
                    (:categoryId IS NULL OR e.categoryId = :categoryId) AND 
                    (:searchTerm IS NULL OR 
                        LOWER(e.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR 
                        LOWER(e.description) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR 
                        LOWER(e.location) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND 
                    e.dateTime >= :currentDateTime
                ORDER BY e.dateTime
            """)
    Page<Event> findAllFiltered(
            @Param("categoryId") Long categoryId,
            @Param("searchTerm") String searchTerm,
            @Param("currentDateTime") LocalDateTime currentDateTime,
            Pageable pageable
    );

}
