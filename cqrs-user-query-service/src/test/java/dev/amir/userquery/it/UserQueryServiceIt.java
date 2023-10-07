package dev.amir.userquery.it;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
import dev.amir.userquery.domain.exception.UserNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class UserQueryServiceIt {

    @MockBean
    UserOutputPort userOutputPortMock;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_GetAllUsers() throws Exception {

        List<User> mockUsers = Arrays.asList(
                new User() {{
                    setId("1");
                    setName("kevin");
                }},
                new User() {{
                    setId("2");
                    setName("john");
                }}
        );

        when(userOutputPortMock.getAll()).thenReturn(mockUsers);

        mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].name").value("kevin"))
                .andExpect(jsonPath("$.users[1].name").value("john"))
                .andExpect(jsonPath("$.users[0].id").value("1"))
                .andExpect(jsonPath("$.users[1].id").value("2"));

        verify(userOutputPortMock).getAll();
    }

    @Test
    public void test_GetUsersById() throws Exception {
        User mockUser = new User();
        String expectedUuid = UUID.randomUUID().toString();
        mockUser.setName("kevin");
        mockUser.setId(expectedUuid);
        when(userOutputPortMock.getById(any(String.class))).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/user/{userId}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.name").value("kevin"))
                .andExpect(jsonPath("$.user.id").value(expectedUuid))
                .andExpect(status().isOk());

        verify(userOutputPortMock).getById(eq(expectedUuid));
    }

    @Test
    public void test_HandleUnknownException() throws Exception {
        when(userOutputPortMock.getAll()).thenThrow(InternalError.class);

        mockMvc.perform(get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal error"));

        verify(userOutputPortMock).getAll();
    }

    @Test
    public void test_HandleUserNotFoundException() throws Exception {
        String expectedUuid = UUID.randomUUID().toString();
        UserNotFoundException ex = new UserNotFoundException(expectedUuid);

        when(userOutputPortMock.getById(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/{userId")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        assertEquals("User with id:" + expectedUuid + " Not found", ex.getMessage());
        verify(userOutputPortMock).getById(any(String.class));
    }
}