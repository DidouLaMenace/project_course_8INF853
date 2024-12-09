package com.prixbanque.inventory_ms.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InventoryDTO {
    private Long id;
    private Long userId;
    private LocalDateTime EventDate;
    private String Name;
}
