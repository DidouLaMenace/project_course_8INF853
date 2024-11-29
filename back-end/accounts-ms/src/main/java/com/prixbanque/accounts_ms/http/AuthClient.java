package com.prixbanque.accounts_ms.http;


import jakarta.ws.rs.QueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "auth")
public interface AuthClient {

    @PostMapping("/register")
    ResponseEntity<Long> register(@RequestParam String email, @RequestParam String password);

    @GetMapping("/getEmail")
    ResponseEntity<String> getEmail(@RequestParam Long userId);
}
