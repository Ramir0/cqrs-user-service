package dev.amir.usercommand.framework.input.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JwtServiceImpl.class)
@TestPropertySource(properties = {
        "security.jwt.secret-key=SECRET_KEY_ONLY_FOR_UNIT_TESTS_PURPOSES",
})
class JwtServiceImplTest {
    private static String DEFAULT_VALID_TOKEN;
    private static String EXPIRED_TOKEN;
    private static String INVALID_PERMISSIONS_TOKEN;

    @Autowired
    private JwtServiceImpl underTest;

    @BeforeAll
    static void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File validTokenFile = ResourceUtils.getFile("classpath:data/valid-token.json");
        DEFAULT_VALID_TOKEN = mapper.readTree(validTokenFile).get("token").asText();

        File expiredTokenFile = ResourceUtils.getFile("classpath:data/expired-token.json");
        EXPIRED_TOKEN = mapper.readTree(expiredTokenFile).get("token").asText();

        File invalidPermissionsToken = ResourceUtils.getFile("classpath:data/invalid-permissions-token.json");
        INVALID_PERMISSIONS_TOKEN = mapper.readTree(invalidPermissionsToken).get("token").asText();
    }

    @AfterAll
    static void cleanUp() {
        DEFAULT_VALID_TOKEN = null;
        EXPIRED_TOKEN = null;
    }

    @Test
    void test_ExtractUserId() {
        String expected = "FEBB810D-AA2A-45C0-87EB-4425D128DE15";
        String token = getDefaultToken();
        String userId = underTest.extractUserId(token);

        assertEquals(expected, userId);
    }

    @Test
    void test_ExtractUsername() {
        String expected = "NewUsername";
        String token = getDefaultToken();
        String username = underTest.extractUsername(token);

        assertEquals(expected, username);
    }

    @Test
    void test_ExtractPermissions() {
        String token = getDefaultToken();
        Set<String> permissions = underTest.extractPermissions(token);

        assertEquals(2, permissions.size());
    }

    @Test
    void test_ExtractPermissions_ShouldReturnAnEmptySet() {
        String token = getInvalidPermissionsToken();
        Set<String> permissions = underTest.extractPermissions(token);

        assertTrue(permissions.isEmpty());
    }

    @Test
    void test_ExtractUsername_ShouldThrowExpiredJwtException() {
        String token = getExpiredToken();

        assertThrows(ExpiredJwtException.class, () -> underTest.extractUsername(token));
    }

    private String getDefaultToken() {
        return DEFAULT_VALID_TOKEN;
    }

    private String getExpiredToken() {
        return EXPIRED_TOKEN;
    }

    private String getInvalidPermissionsToken() {
        return INVALID_PERMISSIONS_TOKEN;
    }
}