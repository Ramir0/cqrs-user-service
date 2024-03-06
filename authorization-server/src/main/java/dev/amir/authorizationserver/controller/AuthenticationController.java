package dev.amir.authorizationserver.controller;

import dev.amir.authorizationserver.request.AuthenticationRequest;
import dev.amir.authorizationserver.response.AuthenticationResponse;
import dev.amir.authorizationserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse jwt = authService.login(request);
        return ResponseEntity.ok(jwt);
    }
}
