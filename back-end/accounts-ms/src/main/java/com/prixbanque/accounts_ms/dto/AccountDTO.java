package com.prixbanque.accounts_ms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    
    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    private Double balance;
}
