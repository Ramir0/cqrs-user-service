package dev.amir.usercommand.framework.input.rest.handler;

import dev.amir.usercommand.application.port.input.UserInputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.domain.valueobject.UserPassword;
import dev.amir.usercommand.framework.input.rest.mapper.UserRequestMapper;
import dev.amir.usercommand.framework.input.rest.request.ChangePasswordRequest;
import dev.amir.usercommand.framework.input.rest.request.UpdateProfileRequest;
import dev.amir.usercommand.util.RandomObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyProfileHandlerImplTest {
    @Mock
    private UserInputPort userInputPortMock;

    @Mock
    private UserRequestMapper requestMapperMock;

    @InjectMocks
    private MyProfileHandlerImpl underTest;

    @Test
    void test_Handle_WhenInputIsChangePasswordRequest() {
        UserId userId = RandomObject.nextObject(UserId.class);
        ChangePasswordRequest request = RandomObject.nextObject(ChangePasswordRequest.class);

        doNothing().when(userInputPortMock).changeUserPassword(any(UserId.class), any(UserPassword.class));

        underTest.handle(request, userId);

        verify(userInputPortMock).changeUserPassword(eq(userId), eq(request.password()));
    }

    @Test
    void test_Handle_WhenInputIsUpdateProfileRequest() {
        UserId userId = RandomObject.nextObject(UserId.class);
        UpdateProfileRequest request = RandomObject.nextObject(UpdateProfileRequest.class);
        User user = new User();

        when(requestMapperMock.convert(any(UpdateProfileRequest.class))).thenReturn(user);
        doNothing().when(userInputPortMock).updateUserProfile(any(User.class));

        underTest.handle(request, userId);

        verify(requestMapperMock).convert(eq(request));
        verify(userInputPortMock).updateUserProfile(eq(user));
    }
}
