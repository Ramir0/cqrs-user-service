package dev.amir.userquery.application.usecase;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserCommandUseCasesTest {

    @Mock
    private UserOutputPort userOutputPortMock;

    @InjectMocks
    private UserCommandUseCases useCases;

    @Test
    void test_saveUser() {
        User inputUser = new User();
        User expected = new User();
        when(userOutputPortMock.save(any(User.class))).thenReturn(expected);
        User actual = useCases.saveUser(expected);

        assertEquals(expected, actual);
        verify(userOutputPortMock).save(eq(inputUser));
    }
}
