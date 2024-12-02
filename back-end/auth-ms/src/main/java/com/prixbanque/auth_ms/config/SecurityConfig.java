package com.prixbanque.auth_ms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/index.html", "/static/**", "/favicon.ico").permitAll() // Routes publiques
                .requestMatchers("/auth/**").permitAll() // Autoriser toutes les routes commençant par /auth/
                .anyRequest().authenticated() // Toutes les autres routes nécessitent une authentification
            )
            .formLogin(form -> form.disable()) // Désactiver le formulaire d'authentification par défaut
            .httpBasic(httpBasic -> httpBasic.disable()); // Désactiver l'authentification basique

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
