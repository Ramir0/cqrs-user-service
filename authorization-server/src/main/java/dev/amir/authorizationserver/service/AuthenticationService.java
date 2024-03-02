package dev.amir.authorizationserver.service;

import dev.amir.authorizationserver.request.AuthenticationRequest;
import dev.amir.authorizationserver.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request);
}
