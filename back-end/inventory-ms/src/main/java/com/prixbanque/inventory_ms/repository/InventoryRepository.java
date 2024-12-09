package com.prixbanque.inventory_ms.repository;

import com.prixbanque.inventory_ms.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByUserId(Long userId);
}
