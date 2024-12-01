package com.prixbanque.auth_ms.controller;

import com.prixbanque.auth_ms.model.User;
import com.prixbanque.auth_ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
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
    public ResponseEntity<Long> register(@RequestParam String email, @RequestParam String password) {
        return ResponseEntity.ok(userService.register(email, password).getUserId());
    }

    // @PostMapping("/register")
    // public ResponseEntity<Long> register(@RequestBody User user) {
    //     return ResponseEntity.ok(userService.register(user.getEmail(), user.getPassword()).getUserId());
    // }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        User user = userService.login(email, password);
        if (user == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok("Login successful");
    }
}
