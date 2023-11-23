package com.onlinestore.api.auth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Component
public class TokenGenerator {
    private final JwtEncoder jwtEncoder;
    private JwtEncoder jwtRefreshTokenEncoder;
    @Autowired
    void setJwtRefreshTokenEncoder(@Qualifier("refreshTokenJwtEncoder") JwtEncoder jwtRefreshTokenEncoder) {
        this.jwtRefreshTokenEncoder = jwtRefreshTokenEncoder;
    }
    public TokenGenerator(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateAccessToken(String id, String scope) {
        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(id)
                .issuer("public")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.SECONDS))
                .subject("Access Token")
                .audience(List.of("Public Client"))
                .claim("scope", scope)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public String generateRefreshToken(String id, String scope, Instant expiresAt) {
        Instant now = Instant.now();
        Duration duration = Duration.between(now, expiresAt);
        if (duration.toDays() < 7) {
            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .id(id)
                    .issuer("public")
                    .issuedAt(now)
                    .subject("Refresh Token")
                    .audience(List.of("Public Client"))
                    .claim("scope", scope)
                    .expiresAt(now.plus(30, ChronoUnit.DAYS))
                    .build();
            return jwtRefreshTokenEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        }
        return null; // Return null if the existing refresh token doesn't require regeneration
    }
}