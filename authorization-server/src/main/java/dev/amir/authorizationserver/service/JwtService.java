package dev.amir.authorizationserver.service;

import java.util.Map;

public interface JwtService {
    String generateToken(String username, Map<String, Object> claims);
}
