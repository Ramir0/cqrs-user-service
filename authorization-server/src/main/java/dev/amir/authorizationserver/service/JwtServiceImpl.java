package dev.amir.authorizationserver.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    private static final String JWT_TYPE = "JWT";

    @Value("${security.jwt.expiration:30}")
    private long expirationMinutes;

    @Value("${security.jwt.secret-key}")
    private String jwtKey;

    @Override
    public String generateToken(String username, Map<String, Object> claims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(calculateExpirationDate(issuedAt.getTime()));
        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .header().type(JWT_TYPE)
                .and()
                .signWith(generateKey())
                .compact();
    }

    private long calculateExpirationDate(long issuedAtInMillis) {
        return issuedAtInMillis + (expirationMinutes * 60 * 1000);
    }

    private Key generateKey() {
        return Keys.hmacShaKeyFor(jwtKey.getBytes());
    }
}
