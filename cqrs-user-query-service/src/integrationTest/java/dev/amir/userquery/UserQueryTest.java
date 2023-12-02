package dev.amir.userquery;

import dev.amir.userquery.framework.output.sql.entity.UserJpa;
import dev.amir.userquery.framework.output.sql.repository.UserJpaRepository;
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
        List<UserJpa> mockUsers = Arrays.asList(
                new UserJpa() {{
                    setId("1");
                    setName("kevin");
                }},
                new UserJpa() {{
                    setId("2");
                    setName("john");
                }}
        );

        when(userJpaRepositoryMock.findAll()).thenReturn(mockUsers);

        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].name").value("kevin"))
                .andExpect(jsonPath("$.users[1].name").value("john"))
                .andExpect(jsonPath("$.users[0].id").value("1"))
                .andExpect(jsonPath("$.users[1].id").value("2"));

        verify(userJpaRepositoryMock).findAll();
    }

    @Test
    public void test_GetUsersById() throws Exception {
        UserJpa mockUser = new UserJpa();
        String expectedUuid = UUID.randomUUID().toString();
        mockUser.setName("kevin");
        mockUser.setId(expectedUuid);
        when(userJpaRepositoryMock.findById(any(String.class))).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/{userId}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.name").value("kevin"))
                .andExpect(jsonPath("$.user.id").value(expectedUuid))
                .andExpect(status().isOk());

        verify(userJpaRepositoryMock).findById(eq(expectedUuid));
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
        String expectedUuid = UUID.randomUUID().toString();
        when(userJpaRepositoryMock.findById(any(String.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{userId}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));

        verify(userJpaRepositoryMock).findById(eq(expectedUuid));
    }
}
