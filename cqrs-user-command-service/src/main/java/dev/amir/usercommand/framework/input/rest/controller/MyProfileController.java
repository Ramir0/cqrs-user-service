package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.framework.input.rest.handler.MyProfileHandler;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class MyProfileController {
    private final MyProfileHandler profileHandler;

    @PatchMapping("/password")
    public ResponseEntity<Void> changeMyPassword(
            @NotNull @PathVariable UUID id,
            @Valid @RequestBody ChangePasswordRequest request
    ) {
        profileHandler.handle(request, id);
        return ResponseEntity.noContent().build();
    }
}