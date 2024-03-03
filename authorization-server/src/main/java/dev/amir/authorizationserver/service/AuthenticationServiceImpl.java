package dev.amir.authorizationserver.service;

import dev.amir.authorizationserver.model.User;
import dev.amir.authorizationserver.request.AuthenticationRequest;
import dev.amir.authorizationserver.response.AuthenticationResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        );
        var authentication = authManager.authenticate(authToken);
        String jwt = jwtService.generateToken(authToken.getName(), generateClaims(authentication));
        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateClaims(Authentication authentication) {
        if (authentication.getPrincipal() instanceof User user) {
            return Map.of(
                    "name", user.getName(),
                    "role", user.getRole().getName(),
                    "permissions", authentication.getAuthorities()
            );
        }

        return Map.of();
    }
}
