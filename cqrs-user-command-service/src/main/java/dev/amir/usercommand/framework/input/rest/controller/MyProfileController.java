package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.input.rest.handler.MyProfileHandler;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/profile")
public class MyProfileController {
    private final MyProfileHandler profileHandler;

    @PatchMapping("/password")
    public ResponseEntity<Void> changeMyPassword(
            @NotNull @PathVariable UserId id,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        profileHandler.handle(request, id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateProfile(
            @NotNull @PathVariable UserId id,
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        log.info("Received request to update");
        profileHandler.handle(request, id);
        log.info("User with ID {} has been successfully update", id);
        return ResponseEntity.noContent().build();
    }
}
