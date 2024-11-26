package com.prixbanque.project.auth;

import com.prixbanque.project.auth.model.User;
import com.prixbanque.project.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Supprime les données existantes pour éviter les duplications
        userRepository.deleteAll();

        // Ajoute des utilisateurs par défaut
        User user1 = new User(null, "user1@example.com", "password1");
        User user2 = new User(null, "user2@example.com", "password2");

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("Users initialized in auth_db");
    }
}
