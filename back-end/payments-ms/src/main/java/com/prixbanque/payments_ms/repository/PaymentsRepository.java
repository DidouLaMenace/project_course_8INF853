package com.prixbanque.payments_ms.repository;

import com.prixbanque.payments_ms.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
