package org.georgiev.tourwhiz.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.factories.UserDtoTestFactory;
import org.georgiev.tourwhiz.factories.VenueDtoTestFactory;
import org.georgiev.tourwhiz.mappers.UserMapper;
import org.georgiev.tourwhiz.mappers.VenueMapper;
import org.georgiev.tourwhiz.presentation.dtos.CreateUserDto;
import org.georgiev.tourwhiz.presentation.dtos.LoginRequestDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateVenueDto;
import org.georgiev.tourwhiz.presentation.dtos.VenueDto;
import org.georgiev.tourwhiz.services.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VenueControllerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;
    @Autowired
    private VenueService venueService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VenueMapper venueMapper;
    private VenueDto venueDto;
    private UpdateVenueDto updateVenueDto;
    private Venue venue;
    private String token;

    @BeforeEach
    void setUp() throws Exception {
        CreateUserDto createUserDto = UserDtoTestFactory.createCreateUserDtoWithSetParameters();
        LoginRequestDto loginRequestDto = UserDtoTestFactory.createLoginRequestDtoWithSetParameters();
        venueDto = VenueDtoTestFactory.createVenueDtoWithSetParameters();
        updateVenueDto = VenueDtoTestFactory.createUpdateVenueDtoWithSetParameters();

        User user = userMapper.createDtoToEntity(createUserDto);
        venue = venueMapper.dtoToEntity(venueDto);

        mvc.perform(post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createUserDto)));

        MvcResult loginResult = mvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequestDto))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(loginRequestDto.getUsername()))
                .andReturn();

        String content = loginResult.getResponse().getContentAsString();
        token = JsonPath.read(content, "$.token");
    }

    @Test
    void createVenueWithLoggedUserCreatesAndReturnsIt() throws Exception {
        mvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venueDto))
                        .header("Authorization", token)
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(venueDto.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(venueDto.getPhoneNumber()));
    }

    @Test
    void deleteExistingVenueDeletesAndReturnsNoContent() throws Exception {
        Venue toDelete = venueService.create(venue);
        Long toDeleteId = toDelete.getId();

        mvc.perform(delete("/venues/%d".formatted(toDeleteId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(toDeleteId))
                .header("Authorization", token)
        ).andExpect(status().isNoContent());
    }

    @Test
    void updateExistingVenueUpdatesAndReturnsUpdatedVenue() throws Exception {
        mvc.perform(post("/venues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venueDto))
                        .header("Authorization", token));

        mvc.perform(put("/venues/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateVenueDto))
                        .header("Authorization", token)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(updateVenueDto.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(updateVenueDto.getPhoneNumber()));
    }
}
