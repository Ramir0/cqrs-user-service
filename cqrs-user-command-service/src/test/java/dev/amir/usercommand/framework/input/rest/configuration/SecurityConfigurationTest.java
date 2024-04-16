package dev.amir.usercommand.framework.input.rest.configuration;

import dev.amir.usercommand.framework.input.rest.filter.AuthenticationFilter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityConfigurationTest {
    @Mock
    private AuthenticationFilter authFilterMock;
    @InjectMocks
    private SecurityConfiguration underTest;

    @Test
    void test_SecurityFilterChain() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class);
        DefaultSecurityFilterChain expected = new DefaultSecurityFilterChain(null, List.of());

        when(http.csrf(any())).thenReturn(http);
        when(http.sessionManagement(any())).thenReturn(http);
        when(http.authorizeHttpRequests(any())).thenReturn(http);
        when(http.addFilterBefore(any(), any())).thenReturn(http);
        when(http.build()).thenReturn(expected);

        SecurityFilterChain filterChain = underTest.securityFilterChain(http);

        assertNotNull(filterChain);
        verify(http).csrf(any());
        verify(http).sessionManagement(any());
        verify(http).authorizeHttpRequests(any());
        verify(http).addFilterBefore(eq(authFilterMock), eq(UsernamePasswordAuthenticationFilter.class));
    }

    @Test
    void test_BuildCsrf() {
        Customizer<CsrfConfigurer<HttpSecurity>> customizer = underTest.buildCsrf();

        assertNotNull(customizer);
    }

    @Test
    void test_BuildSessionManagement() {
        SessionManagementConfigurer<HttpSecurity> configure = mock();
        when(configure.sessionCreationPolicy(any())).thenReturn(null);

        Customizer<SessionManagementConfigurer<HttpSecurity>> customizer = underTest.buildSessionManagement();
        customizer.customize(configure);

        assertNotNull(customizer);
        verify(configure).sessionCreationPolicy(eq(SessionCreationPolicy.STATELESS));
    }

    @Test
    void test_BuildAuthorizeRequests() {
        ReflectionTestUtils.setField(underTest, "securityEnabled", true);

        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = mock();
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configure = mock();

        when(configure.requestMatchers(any(), any())).thenReturn(authorizedUrl);
        when(authorizedUrl.hasAuthority(any())).thenReturn(null);

        when(configure.anyRequest()).thenReturn(authorizedUrl);
        when(authorizedUrl.authenticated()).thenReturn(null);

        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizer
                = underTest.buildAuthorizeRequests();
        customizer.customize(configure);

        assertNotNull(customizer);

        verify(configure).requestMatchers(eq(HttpMethod.POST), eq(new String[]{"/users"}));
        verify(authorizedUrl).hasAuthority(eq("Create Users"));

        verify(configure).anyRequest();
        verify(authorizedUrl).authenticated();
    }

    @Test
    void test_BuildAuthorizeRequests_ShouldPermitAllRequests_WhenSecurityIsDisabled() {
        ReflectionTestUtils.setField(underTest, "securityEnabled", false);

        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = mock();
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry configure = mock();

        when(configure.anyRequest()).thenReturn(authorizedUrl);
        when(authorizedUrl.permitAll()).thenReturn(null);

        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizer
                = underTest.buildAuthorizeRequests();
        customizer.customize(configure);

        assertNotNull(customizer);
        verify(configure).anyRequest();
        verify(authorizedUrl).permitAll();
    }
}
