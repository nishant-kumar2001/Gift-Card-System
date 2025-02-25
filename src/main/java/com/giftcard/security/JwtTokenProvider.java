package com.giftcard.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
//    private final String secretKey = "your-very-secret-key-for-jwt-which-should-be-long";
//    private final long expirationMs = 86400000; // 24 hours

    @Value("${jwt.secret}")
    private String secretKey;


    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private Key key;

    @PostConstruct // Initialize after properties are injected
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void printProperties() {
//        System.out.println("JWT Secret: " + secretKey);
//        System.out.println("JWT Expiration: " + expirationMs);
//    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
