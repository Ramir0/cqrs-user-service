package dev.amir.usercommand.framework.input.rest.service;

import java.util.Set;

public interface JwtService {
    String extractUserId(String jwt);

    String extractUsername(String jwt);

    Set<String> extractPermissions(String jwt);
}
