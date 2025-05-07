package com.app.f1x.security.jwt;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

public class JwtUtils {

    private static final Logger logger = Logger.getLogger(JwtUtils.class);

    @Value("${com.f1x.jwt-secret}")
    private String jwtSecret;

    @Value("${com.f1x.jwt-expiration-ms}")
    private Integer jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal
    }

}
