package dev.amir.usercommand.framework.input.rest.filter;

import dev.amir.usercommand.framework.input.rest.service.JwtService;
import dev.amir.usercommand.util.RandomObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {
    @Mock
    private JwtService jwtServiceMock;
    @InjectMocks
    private JwtAuthenticationFilter underTest;

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "Header", "123"})
    @NullSource
    void test_DoFilterInternal_ShouldNotValidateToken_WhenAuthHeaderIsInvalid(String authHeaderValue)
            throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader(anyString())).thenReturn(authHeaderValue);

        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        HttpServletResponse response = mock(HttpServletResponse.class);

        underTest.doFilterInternal(request, response, filterChain);

        verify(request).getHeader(eq("Authorization"));
        verify(filterChain).doFilter(eq(request), eq(response));
        verify(jwtServiceMock, never()).extractUserId(any());
        verify(jwtServiceMock, never()).extractUsername(any());
        verify(jwtServiceMock, never()).extractPermissions(any());
    }

    @Test
    void test_DoFilterInternal_ShouldValidateToken_WhenAuthHeaderIsValid() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        String authHeaderValue = "Bearer 123ABC";
        when(request.getHeader(anyString())).thenReturn(authHeaderValue);

        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        String userId = UUID.randomUUID().toString();
        when(jwtServiceMock.extractUserId(any())).thenReturn(userId);

        String username = RandomObject.nextObject(String.class);
        when(jwtServiceMock.extractUsername(any())).thenReturn(username);

        Set<String> permissions = Set.of("Permission1", "Permission2");
        when(jwtServiceMock.extractPermissions(any())).thenReturn(permissions);

        HttpServletResponse response = mock(HttpServletResponse.class);

        underTest.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        verify(request).getHeader(eq("Authorization"));
        verify(filterChain).doFilter(eq(request), eq(response));
        verify(jwtServiceMock).extractUserId(anyString());
        verify(jwtServiceMock).extractUsername(anyString());
        verify(jwtServiceMock).extractPermissions(anyString());
    }

    @Test
    void test_DoFilterInternal_ShouldNotValidateToken_WhenContextHasAuthentication()
            throws ServletException, IOException {
        UsernamePasswordAuthenticationToken existingAuthentication = new UsernamePasswordAuthenticationToken(
                null,
                null,
                null
        );
        SecurityContextHolder.getContext().setAuthentication(existingAuthentication);

        HttpServletRequest request = mock(HttpServletRequest.class);
        String authHeaderValue = "Bearer 123ABC";
        when(request.getHeader(anyString())).thenReturn(authHeaderValue);

        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));

        HttpServletResponse response = mock(HttpServletResponse.class);

        underTest.doFilterInternal(request, response, filterChain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals(existingAuthentication, authentication);
        verify(request).getHeader(eq("Authorization"));
        verify(jwtServiceMock, never()).extractUserId(any());
        verify(jwtServiceMock, never()).extractUsername(any());
        verify(jwtServiceMock, never()).extractPermissions(any());
    }
}
