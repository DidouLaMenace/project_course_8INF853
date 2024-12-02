package com.prixbanque.gateway; // Remplacez par votre package si différent

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // Permet toutes les origines, ou remplacez par 'http://localhost:3000'
        config.addAllowedMethod("*");       // Permet toutes les méthodes HTTP (GET, POST, etc.)
        config.addAllowedHeader("*");       // Permet tous les headers
        config.setAllowCredentials(true);   // Autorise l'envoi des cookies ou identifiants
        config.addAllowedOrigin("http://localhost:3000"); // Origine de React

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
