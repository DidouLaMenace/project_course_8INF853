package com.prixbanque.payments_ms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long userId;
    private Double totalAmount;
}
