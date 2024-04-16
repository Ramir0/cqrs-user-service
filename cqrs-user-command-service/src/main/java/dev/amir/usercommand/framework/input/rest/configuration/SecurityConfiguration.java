package dev.amir.usercommand.framework.input.rest.configuration;

import dev.amir.usercommand.framework.input.rest.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationFilter authFilter;

    @Value("${security.enabled:true}")
    private boolean securityEnabled;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(buildCsrf())
                .sessionManagement(buildSessionManagement())
                .authorizeHttpRequests(buildAuthorizeRequests())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    Customizer<CsrfConfigurer<HttpSecurity>> buildCsrf() {
        return AbstractHttpConfigurer::disable;
    }

    Customizer<SessionManagementConfigurer<HttpSecurity>> buildSessionManagement() {
        return config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
            buildAuthorizeRequests() {
        if (!securityEnabled) {
            return config -> config.anyRequest().permitAll();
        }

        return config -> {
            configureUserController(config);
            config.anyRequest().authenticated();
        };
    }

    private void configureUserController(
            AuthorizeHttpRequestsConfigurer<?>.AuthorizationManagerRequestMatcherRegistry config
    ) {
        config.requestMatchers(HttpMethod.POST, "/users").hasAuthority("Create Users");
    }
}
