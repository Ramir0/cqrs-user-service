package dev.amir.usercommand.application.aop;

import dev.amir.usercommand.domain.entity.User;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OnUserAspectTest {
    private OnUserAspect underTest;

    @BeforeEach
    void setUp() {
        underTest = new OnUserAspect();
    }

    @Test
    void test_onUserCreation_SetCreatedAt() {
        User user = new User();
        Object[] args = {user};
        JoinPoint joinPointMock = mock(JoinPoint.class);

        when(joinPointMock.getArgs()).thenReturn(args);

        underTest.onUserCreation(joinPointMock);

        assertNotNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
        verify(joinPointMock).getArgs();
    }

    @Test
    void test_onUserUpdate_SetUpdatedAt() {
        User user = new User();
        Object[] args = {user};
        JoinPoint joinPointMock = mock(JoinPoint.class);

        when(joinPointMock.getArgs()).thenReturn(args);

        underTest.onUserUpdate(joinPointMock);

        assertNotNull(user.getUpdatedAt());
        assertNull(user.getCreatedAt());
        verify(joinPointMock).getArgs();
    }
}
