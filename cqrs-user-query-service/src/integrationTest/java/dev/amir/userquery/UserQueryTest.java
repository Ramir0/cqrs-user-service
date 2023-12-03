package dev.amir.userquery;

import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import dev.amir.userquery.framework.output.sql.repository.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static dev.amir.userquery.util.DefaultObject.defaultUserId;
import static dev.amir.userquery.util.DefaultObject.defaultUserId2;
import static dev.amir.userquery.util.DefaultObject.defaultUserJpa;
import static dev.amir.userquery.util.DefaultObject.defaultUserJpa2;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserQueryTest {
    @MockBean
    private UserJpaRepository userJpaRepositoryMock;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_LoadContext() {
        assertNotNull(userJpaRepositoryMock);
        assertNotNull(mockMvc);
    }

    @Test
    void test_GetAllUsers() throws Exception {
        List<UserJpa> mockUsers = List.of(defaultUserJpa, defaultUserJpa2);
        when(userJpaRepositoryMock.findAll()).thenReturn(mockUsers);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].id").value(defaultUserId))
                .andExpect(jsonPath("$.users[0].name").value(defaultUserJpa.getName()))
                .andExpect(jsonPath("$.users[0].lastname").value(defaultUserJpa.getLastname()))
                .andExpect(jsonPath("$.users[0].status").value(defaultUserJpa.getStatus().name()))
                .andExpect(jsonPath("$.users[1].id").value(defaultUserId2))
                .andExpect(jsonPath("$.users[1].name").value(defaultUserJpa2.getName()))
                .andExpect(jsonPath("$.users[1].lastname").value(defaultUserJpa2.getLastname()))
                .andExpect(jsonPath("$.users[1].status").value(defaultUserJpa2.getStatus().name()));

        verify(userJpaRepositoryMock).findAll();
    }

    @ParameterizedTest
    @MethodSource("dataProvider_getUserById")
    public void test_GetUserById(String userId, UserJpa userJpa) throws Exception {
        when(userJpaRepositoryMock.findById(any(String.class))).thenReturn(Optional.of(userJpa));

        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.name").value(userJpa.getName()))
                .andExpect(jsonPath("$.user.lastname").value(userJpa.getLastname()))
                .andExpect(jsonPath("$.user.status").value(userJpa.getStatus().name()))
                .andExpect(jsonPath("$.user.id").value(userId))
                .andExpect(status().isOk());

        verify(userJpaRepositoryMock).findById(eq(userId));
    }

    @Test
    public void test_HandleUnknownException_ForGetAllUsers() throws Exception {
        when(userJpaRepositoryMock.findAll()).thenThrow(InternalError.class);

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));

        verify(userJpaRepositoryMock).findAll();
    }

    @Test
    public void test_HandleUserNotFoundException_ForGetUserById() throws Exception {
        when(userJpaRepositoryMock.findById(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{userId}", defaultUserId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userJpaRepositoryMock).findById(eq(defaultUserId));
    }

    private static Stream<Arguments> dataProvider_getUserById() {
        return Stream.of(
                Arguments.of(defaultUserId, defaultUserJpa),
                Arguments.of(defaultUserId2, defaultUserJpa2)
        );
    }
}
