package dev.amir.usercommand.framework.input.rest.controller;

import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.framework.input.rest.handler.MyProfileHandler;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MyProfileControllerTest {
    @Mock
    private MyProfileHandler myProfileHandlerMock;

    @InjectMocks
    private MyProfileController underTest;

    @Test
    void test_ChangePassword() {
        UserId userId = RandomObject.nextObject(UserId.class);
        ChangePasswordRequest request = RandomObject.nextObject(ChangePasswordRequest.class);
        doNothing().when(myProfileHandlerMock).handle(any(ChangePasswordRequest.class), any(UserId.class));

        ResponseEntity<Void> actual = underTest.changeMyPassword(userId, request);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertNull(actual.getBody());
        verify(myProfileHandlerMock).handle(eq(request), eq(userId));
    }

    @Test
    void test_UpdateProfile() {
        UserId userId = RandomObject.nextObject(UserId.class);
        UpdateProfileRequest request = RandomObject.nextObject(UpdateProfileRequest.class);
        doNothing().when(myProfileHandlerMock).handle(any(UpdateProfileRequest.class), any(UserId.class));

        ResponseEntity<Void> actual = underTest.updateProfile(userId, request);

        assertEquals(HttpStatus.NO_CONTENT, actual.getStatusCode());
        assertNull(actual.getBody());
        verify(myProfileHandlerMock).handle(eq(request), eq(userId));
    }
}
