package org.georgiev.tourwhiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.georgiev.tourwhiz.presentation.dtos.CreateUserDto;
import org.georgiev.tourwhiz.presentation.dtos.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.georgiev.tourwhiz.factories.UserDtoTestFactory.createCreateUserDtoWithSetParameters;
import static org.georgiev.tourwhiz.factories.UserDtoTestFactory.createLoginRequestDtoWithSetParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserAuthenticationTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    private CreateUserDto createUserDto;
    private LoginRequestDto loginRequestDto;

    @BeforeEach
    void setUp() throws Exception {
        createUserDto = createCreateUserDtoWithSetParameters();
        loginRequestDto = createLoginRequestDtoWithSetParameters();
    }

    @Test
    void whenSigningUpWithValidUserShouldCreateUser() throws Exception {

        mvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(createUserDto.getUsername()))
                .andExpect(jsonPath("$.firstName").value(createUserDto.getFirstName()));
    }

    @Test
    void whenSigningInReturnsTokenMatchingLoggedUser() throws Exception {
        mvc.perform(post("/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(createUserDto.getUsername()))
                .andExpect(jsonPath("$.firstName").value(createUserDto.getFirstName()));

        mvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(loginRequestDto.getUsername()));
    }
}
