package dev.amir.usercommand.framework.input.rest.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.secret-key}")
    private String jwtKey;

    @Override
    public String extractUserId(String token) {
        return extractClaim(token, (claims) -> claims.get("id", String.class));
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public Set<String> extractPermissions(String token) {
        var permissionsList = extractClaim(token, (claims -> claims.get("permissions", ArrayList.class)));
        Set<String> permissions = new HashSet<>();
        for (Object permissionObject : permissionsList) {
            if (permissionObject instanceof Map<?, ?> authority) {
                Optional<?> authValue = authority.values().stream().findFirst();
                authValue.ifPresent(value -> permissions.add(value.toString()));
            }
        }
        return permissions;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtKey.getBytes());
    }
}
