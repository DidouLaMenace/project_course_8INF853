package com.prixbanque.booking_ms.repository;

import com.prixbanque.booking_ms.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserIdAndEventId(Long userId, Long eventId);
}
