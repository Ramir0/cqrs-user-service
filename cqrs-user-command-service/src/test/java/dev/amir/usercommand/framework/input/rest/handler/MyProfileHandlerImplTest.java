package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.util.RandomObject;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MyProfileHandlerImplTest {
    @Mock
    private UserInputPort userInputPortMock;

    @InjectMocks
    private MyProfileHandlerImpl underTest;

    @Test
    void test_Handle_WhenInputIsChangePasswordRequest() {
        UUID userId = UUID.randomUUID();
        ChangePasswordRequest request = RandomObject.nextObject(ChangePasswordRequest.class);

        doNothing().when(userInputPortMock).changeUserPassword(any(UUID.class), any(UserPassword.class));

        underTest.handle(request, userId);

        verify(userInputPortMock).changeUserPassword(eq(userId), eq(request.password()));
    }
}
