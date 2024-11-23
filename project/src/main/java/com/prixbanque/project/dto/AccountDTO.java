package com.prixbanque.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
}
