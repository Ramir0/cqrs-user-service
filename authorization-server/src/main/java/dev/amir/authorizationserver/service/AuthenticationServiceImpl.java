package dev.amir.authorizationserver.service;

import dev.amir.authorizationserver.request.AuthenticationRequest;
import dev.amir.authorizationserver.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        authManager.authenticate(authToken);
        String jwt = jwtService.generateToken(authToken);
        return new AuthenticationResponse(jwt);
    }
}
