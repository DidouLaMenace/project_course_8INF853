package com.prixbanque.auth_ms.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    // Utilisez une clé secrète plus sécurisée (par exemple, stockée dans un système de gestion des secrets)
    private final String SECRET_KEY = "your-very-secure-secret-key"; 
    private final long EXPIRATION_TIME = 86400000; // 1 jour en millisecondes

    // Génère la clé de signature (objet Key)
    private Key getSigningKey() {
        return new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    // Crée un JWT
    public String createToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Utilise la clé sécurisée
                .compact();
    }

    // Valide le JWT et extrait le sujet (email)
    public String validateToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Utilise la clé sécurisée
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
