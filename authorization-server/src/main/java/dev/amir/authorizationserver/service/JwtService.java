package dev.amir.authorizationserver.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface JwtService {
    String generateToken(UsernamePasswordAuthenticationToken request);
}
