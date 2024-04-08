package com.sevmark.SevMark.ControllerTest;
import com.sevmark.SevMark.controller.UserController;
import com.sevmark.SevMark.DTO.UserDTO;
import com.sevmark.SevMark.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        // Mocking userService behavior
        List<UserDTO> mockUsers = Arrays.asList(
                new UserDTO(1L, "antoniocruzgomes880@gmail.com", "Antonio880", "admin", "dono", "8592627366"),
                new UserDTO(2L, "antoniocruzgomes88@gmail.com", "Antonio88", "admin", "cliente", "8592627300")
        );
        when(userService.getAllUsers()).thenReturn(mockUsers);
    }

    @Test
    public void getUsers_ReturnsListOfUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("antoniocruzgomes880@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value("antoniocruzgomes88@gmail.com"));
    }
}
