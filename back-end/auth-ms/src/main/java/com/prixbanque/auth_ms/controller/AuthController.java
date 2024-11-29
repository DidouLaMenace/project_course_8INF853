package com.prixbanque.auth_ms.controller;

import com.prixbanque.auth_ms.model.User;
import com.prixbanque.auth_ms.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Auth service is alive !");
    }

    @GetMapping("/getEmail")
    public ResponseEntity<String> getEmailByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId).getEmail());
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestParam String email, @RequestParam String rawPassword) {
        return ResponseEntity.ok(userService.register(email, rawPassword).getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String rawPassword) {
        User user = userService.login(email, rawPassword);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok("Login successful");
    }
}
