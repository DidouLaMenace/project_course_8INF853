package com.prixbanque.accounts_ms.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("banking")
public interface BankingClient {

    @PostMapping("/accounts/create")
    ResponseEntity<String> createBankAccount(@RequestParam Long ownerUserId);
}
