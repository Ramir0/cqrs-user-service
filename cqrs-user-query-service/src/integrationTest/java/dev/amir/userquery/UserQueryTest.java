package dev.amir.userquery;

import dev.amir.userquery.application.port.output.UserOutputPort;
import dev.amir.userquery.domain.entity.User;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserQueryTest {

    @MockBean
    UserOutputPort userOutputPort;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsersTest() throws Exception {

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

        when(userOutputPort.getAll()).thenReturn(mockUsers);

        mockMvc.perform(MockMvcRequestBuilders.get("/user").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].name").value("kevin"))
                .andExpect(jsonPath("$.users[1].name").value("john"))
                .andExpect(jsonPath("$.users[0].id").value("1"))
                .andExpect(jsonPath("$.users[1].id").value("2"));

        verify(userOutputPort).getAll();
    }

    @Test
    public void getUsersByIdTest() throws Exception {
        User mockUser = new User();
        String expectedUuid = UUID.randomUUID().toString();
        mockUser.setName("kevin");
        mockUser.setId(expectedUuid);
        when(userOutputPort.getById(any(String.class))).thenReturn(Optional.of(mockUser));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", expectedUuid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.user.name").value("kevin"))
                .andExpect(jsonPath("$.user.id").value(expectedUuid))
                .andExpect(status().isOk());

        verify(userOutputPort).getById(eq(expectedUuid));
    }
}
