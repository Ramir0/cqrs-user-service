package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
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
class UserCommandUseCasesTest {

    @Mock
    private UserOutputPort userOutputPortMock;

    @InjectMocks
    private UserCommandUseCases underTest;

    @Test
    void test_SaveUser() {
        User expected = new User();
        doNothing().when(userOutputPortMock).save(any(User.class));
        
        underTest.saveUser(expected);

        verify(userOutputPortMock).save(eq(expected));
    }
}
