package com.prixbanque.auth_ms.service;

import com.prixbanque.auth_ms.model.User;
import com.prixbanque.auth_ms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        //user.setRole(Role.USER); // Par défaut
        user.setRole("USER");
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> encoder.matches(password, user.getPassword()))
                .orElse(null);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
