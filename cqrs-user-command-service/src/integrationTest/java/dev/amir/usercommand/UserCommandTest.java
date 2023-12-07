package dev.amir.usercommand;

import dev.amir.usercommand.domain.valueobject.UserId;
import dev.amir.usercommand.framework.output.sql.entity.UserJpa;
import dev.amir.usercommand.framework.output.sql.repository.UserJpaRepository;
import java.io.File;
import java.nio.charset.StandardCharsets;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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
        UserJpa mockUser = new UserJpa();
        UserId expectedUuid = new UserId();
        mockUser.setId(expectedUuid.getValue());
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(mockUser);

        File responseFile = ResourceUtils.getFile("classpath:responses/create-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(expectedUuid.toString()));

        verify(jpaRepositoryMock).save(any(UserJpa.class));
    }

    @Test
    public void test_DeleteUserTest() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        doNothing().when(jpaRepositoryMock).deleteById(any(UUID.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{id}", expectedUuid))
                .andExpect(status().isNoContent());

        verify(jpaRepositoryMock).existsById(eq(expectedUuid));
        verify(jpaRepositoryMock).deleteById(eq(expectedUuid));
    }

    @Test
    void test_UpdateUserTest() throws Exception {
        UserJpa mockUser = new UserJpa();
        UUID expectedUuid = UUID.randomUUID();
        mockUser.setId(expectedUuid);
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenReturn(mockUser);

        File responseFile = ResourceUtils.getFile("classpath:responses/update-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}", expectedUuid)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(jpaRepositoryMock).existsById(eq(expectedUuid));
        verify(jpaRepositoryMock).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleUnknownExceptionForCreateUser() throws Exception {
        when(jpaRepositoryMock.save(any(UserJpa.class))).thenThrow(InternalError.class);

        File responseFile = ResourceUtils.getFile("classpath:responses/create-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));

        verify(jpaRepositoryMock).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForUpdateUser() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(false);

        File responseFile = ResourceUtils.getFile("classpath:responses/update-users-response.json");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", expectedUuid)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(jpaRepositoryMock).existsById(eq(expectedUuid));
        verify(jpaRepositoryMock, never()).save(any(UserJpa.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForDeleteUser() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        when(jpaRepositoryMock.existsById(any(UUID.class))).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", expectedUuid))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(jpaRepositoryMock).existsById(eq(expectedUuid));
        verify(jpaRepositoryMock, never()).deleteById(eq(expectedUuid));
    }

    @Test
    public void test_HandleBadRequestExceptionForDeleteUser() throws Exception {
        int wrongUuid = 123;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{id}", wrongUuid))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Bad request"));
    }
}
