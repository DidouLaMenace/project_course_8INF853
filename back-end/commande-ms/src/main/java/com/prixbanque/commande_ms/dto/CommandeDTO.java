package com.prixbanque.payments_ms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommandeDTO {
    private Long userId;
    private List<String> tickets; // Correspond aux sièges ou tickets
}
