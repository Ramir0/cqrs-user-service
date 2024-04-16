package dev.amir.usercommand.framework.input.rest.filter;

import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.valueobject.user.UserId;
import dev.amir.usercommand.domain.valueobject.user.Username;
import dev.amir.usercommand.framework.input.rest.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty("security.enabled")
public class JwtAuthenticationFilter extends AuthenticationFilter {
    private static final String JWT_PREFIX = "Bearer ";

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(authHeader) && authHeader.startsWith(JWT_PREFIX)) {
            validateToken(authHeader);
        }
        filterChain.doFilter(request, response);
    }

    private void validateToken(String authHeader) {
        String jwt = authHeader.substring(7);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = new User();
            UserId userId = new UserId(jwtService.extractUserId(jwt));
            user.setId(userId);
            Username username = new Username(jwtService.extractUsername(jwt));
            user.setUsername(username);

            Set<GrantedAuthority> authorities = extractAuthorities(jwt);
            var authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private Set<GrantedAuthority> extractAuthorities(String jwt) {
        return jwtService.extractPermissions(jwt)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
