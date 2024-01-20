package dev.amir.usercommand;

import dev.amir.usercommand.domain.valueobject.UserEmail;
import dev.amir.usercommand.domain.valueobject.UserStatus;
import dev.amir.usercommand.domain.valueobject.UserUsername;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.ResourceUtils;

import static dev.amir.usercommand.util.DefaultObject.defaultUserId;
import static dev.amir.usercommand.util.DefaultObject.defaultUserJpa;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserCommandTest {
    @MockBean
    private UserJpaRepository jpaRepositoryMock;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_LoadContext() {
        assertNotNull(jpaRepositoryMock);
        assertNotNull(mockMvc);
    }

    @Test
    void test_CreateUserTest() throws Exception {
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(defaultUserJpa);
        when(jpaRepositoryMock.existsByEmail(any(UserEmail.class))).thenReturn(false);
        when(jpaRepositoryMock.existsByUsername(any(UserUsername.class))).thenReturn(false);

        File responseFile = ResourceUtils.getFile("classpath:requests/create-users-request.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(defaultUserId.toString()));

        verify(jpaRepositoryMock).save(any(UserJpa.class));
        verify(jpaRepositoryMock).existsByEmail(any(UserEmail.class));
        verify(jpaRepositoryMock).existsByUsername(any(UserUsername.class));

    }

    @Test
    public void test_DeleteUserTest() throws Exception {
        UserJpa userJpa = new UserJpa();
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(userJpa));
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(userJpa);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{id}", defaultUserId))
                .andExpect(status().isNoContent());

        verify(jpaRepositoryMock).findById(eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock).save(eq(userJpa));
    }

    @Test
    void test_UpdateUserTest() throws Exception {
        UserJpa userJpa = new UserJpa();
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(userJpa));
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(userJpa);
        File responseFile = ResourceUtils.getFile("classpath:requests/update-users-request.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}", defaultUserId)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(jpaRepositoryMock).findById(eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock).save(eq(userJpa));
    }

    @Test
    void test_ChangePassword() throws Exception {
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(defaultUserJpa));
        when(jpaRepositoryMock.existsByStatusAndId(any(UserStatus.class), any(UUID.class))).thenReturn(false);
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(defaultUserJpa);
        File responseFile = ResourceUtils.getFile("classpath:requests/change-password-user-request.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/{id}/password", defaultUserId)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(jpaRepositoryMock).findById(eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock).existsByStatusAndId(eq(UserStatus.REMOVED), eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock).save(eq(defaultUserJpa));
    }


    @Test
    public void test_HandleUserNotFoundExceptionForChangePassword() throws Exception {
        when(jpaRepositoryMock.existsByStatusAndId(any(UserStatus.class), any(UUID.class))).thenReturn(false);
        File responseFile = ResourceUtils.getFile("classpath:requests/change-password-user-request.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/users/{id}/password", defaultUserId)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(jpaRepositoryMock).existsByStatusAndId(eq(UserStatus.REMOVED), eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock, never()).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleUnknownExceptionForCreateUser() throws Exception {
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenThrow(InternalError.class);
        when(jpaRepositoryMock.existsByEmail(any(UserEmail.class))).thenReturn(false);
        when(jpaRepositoryMock.existsByUsername(any(UserUsername.class))).thenReturn(false);

        File responseFile = ResourceUtils.getFile("classpath:requests/create-users-request.json");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));

        verify(jpaRepositoryMock).save(any(UserJpa.class));
        verify(jpaRepositoryMock).existsByEmail(any(UserEmail.class));
        verify(jpaRepositoryMock).existsByUsername(any(UserUsername.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForUpdateUser() throws Exception {
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());
        File responseFile = ResourceUtils.getFile("classpath:requests/update-users-request.json");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", defaultUserId)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(jpaRepositoryMock).findById(eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock, never()).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForDeleteUser() throws Exception {
        when(jpaRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", defaultUserId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(jpaRepositoryMock).findById(eq(defaultUserId.getValue()));
        verify(jpaRepositoryMock, never()).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleBadRequestExceptionForInvalidUserIdFormat() throws Exception {
        int wrongUuid = 123;

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", wrongUuid))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Request contains invalid data"));
    }

    @Test
    public void test_HandleBadRequestExceptionForMissingRequestData() throws Exception {
        File responseFile = ResourceUtils.getFile("classpath:requests/create-users-missing-data-request.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("There is missing data in the Request"));

        verify(jpaRepositoryMock, never()).save(any(UserJpa.class));
    }
}
