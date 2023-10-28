package org.georgiev.tourwhiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.mappers.UserMapper;
import org.georgiev.tourwhiz.presentation.dtos.CreateUserDto;
import org.georgiev.tourwhiz.presentation.dtos.LoginRequestDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.georgiev.tourwhiz.factories.UserDtoTestFactory.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserMapper userMapper;
    private UpdateUserDto updateUserDto;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
        CreateUserDto createUserDto = createCreateUserDtoWithSetParameters();
        LoginRequestDto loginRequestDto = createLoginRequestDtoWithSetParameters();
        updateUserDto = createUpdateUserDtoWithSetParameters();

        User user = userMapper.createDtoToEntity(createUserDto);

        mvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        MvcResult loginResult = mvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto)))
                .andReturn();

        String content = loginResult.getResponse().getContentAsString();
        token = JsonPath.read(content, "$.token");
    }

    @Test
    @WithMockUser
    void whenTryingToGetUserThatDoesntExistShouldReturnNotFound() throws Exception {
        mvc.perform(get("/users/411"))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenUpdatingExistingUserShouldReturnUpdatedUser() throws Exception {
        mvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateUserDto))
                        .header("Authorization", token)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(updateUserDto.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(updateUserDto.getPhoneNumber()));
    }
}

