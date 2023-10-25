package dev.amir.usercommand;

import dev.amir.usercommand.application.port.output.UserMessageOutputPort;
import dev.amir.usercommand.application.port.output.UserOutputPort;
import dev.amir.usercommand.domain.entity.User;
import dev.amir.usercommand.domain.exception.UserNotFoundException;
import dev.amir.usercommand.domain.valueobject.UserId;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserCommandTest {
    @MockBean
    UserOutputPort userOutputPortMock;
    @MockBean
    UserMessageOutputPort userMessageOutputPortMock;
    @MockBean
    UserJpaRepository jpaRepositoryMock;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_CreateUserTest() throws Exception {
        User mockUser = new User();
        UserId expectedUuid = new UserId();
        mockUser.setId(expectedUuid);
        when(userOutputPortMock.save(any(User.class))).thenReturn(mockUser);
        doNothing().when(userMessageOutputPortMock).sendMessage(any());

        File responseFile = ResourceUtils.getFile("classpath:responses/create-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedUuid.toString()));

        verify(userOutputPortMock).save(any(User.class));
        verify(userMessageOutputPortMock).sendMessage(any(User.class));
    }

    @Test
    public void test_DeleteUserTest() throws Exception {
        UserId expectedUuid = new UserId();

        doNothing().when(userOutputPortMock).delete(expectedUuid);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/users/{id}", expectedUuid))
                .andExpect(status().isNoContent());

        verify(userOutputPortMock).delete(eq(expectedUuid));
    }

    @Test
    void test_UpdateUserTest() throws Exception {
        User mockUser = new User();
        UserId expectedUuid = new UserId(UUID.randomUUID());
        mockUser.setId(expectedUuid);
        when(userOutputPortMock.update(any(User.class))).thenReturn(mockUser);
        doNothing().when(userMessageOutputPortMock).sendMessage(any());

        File responseFile = ResourceUtils.getFile("classpath:responses/update-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/users/{id}", expectedUuid)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userOutputPortMock).update(any(User.class));
        verify(userMessageOutputPortMock).sendMessage(any(User.class));
    }

    @Test
    public void test_HandleUnknownExceptionForCreateUser() throws Exception {
        when(userOutputPortMock.save(any(User.class))).thenThrow(InternalError.class);

        File responseFile = ResourceUtils.getFile("classpath:responses/create-users-response.json");
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));

        verify(userOutputPortMock).save(any(User.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForUpdateUser() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        when(userOutputPortMock.update(any(User.class))).thenThrow(new UserNotFoundException(expectedUuid));

        File responseFile = ResourceUtils.getFile("classpath:responses/update-users-response.json");

        mockMvc.perform(MockMvcRequestBuilders.put("/users/{userId}", expectedUuid)
                        .content(Files.contentOf(responseFile, StandardCharsets.UTF_8))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userOutputPortMock).update(any(User.class));
    }

    @Test
    public void test_HandleUserNotFoundExceptionForDeleteUser() throws Exception {
        UUID expectedUuid = UUID.randomUUID();
        UserId userId = new UserId(expectedUuid);

        doThrow(UserNotFoundException.class).when(userOutputPortMock).delete(any(UserId.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userOutputPortMock).delete(eq(userId));
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
