package com.lms.dev.security.util;

import com.lms.dev.config.AppProperties;
import com.lms.dev.security.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class JwtUtils {

    private final AppProperties appProperties;

    public JwtUtils(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String generateJwtToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + appProperties.getJwtExpirationMs());
        
        return Jwts.builder()
                .subject(userPrincipal.getEmail())
                .claim("userId", userPrincipal.getId())
                .claim("role", userPrincipal.getAuthorities().iterator().next().getAuthority())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSigningKey())
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromJwtToken(String token) {
        return getClaims(token).getSubject();
    }

    public UUID getUserIdFromJwtToken(String token) {
        return getClaims(token).get("userId", UUID.class);
    }

    public String getRoleFromJwtToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    public boolean validateJwtToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("Invalid JWT: {}", e.getMessage());
            return false;
        }
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(appProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8));
    }
}
