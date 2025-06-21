package com.ianniciello.auth_jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ianniciello.auth_jwt.dto.AuthRequest;
import com.ianniciello.auth_jwt.dto.JwtResponse;
import com.ianniciello.auth_jwt.enums.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerThenLoginReturnsJwt() throws Exception {
        AuthRequest registerRequest = new AuthRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setPassword("Password1");
        registerRequest.setRole(Role.USER);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());

        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("Password1");

        MvcResult result = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String json = result.getResponse().getContentAsString();
        JwtResponse response = objectMapper.readValue(json, JwtResponse.class);
        assertThat(response.getToken()).isNotBlank();
    }
}
