package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.presentation.dtos.CreateUserDto;
import org.georgiev.tourwhiz.presentation.dtos.LoginRequestDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateUserDto;
import org.georgiev.tourwhiz.presentation.dtos.UserDto;

public class UserDtoTestFactory {

    public static User USER_TO_DTO = UserTestFactory.createWithSetParameters();

    public static UserDto createUserDtoWithSetParameters() {
        return new UserDto(USER_TO_DTO.getUsername(), USER_TO_DTO.getFirstName(), USER_TO_DTO.getLastName(),
                USER_TO_DTO.getEmail(), USER_TO_DTO.getPhoneNumber(), USER_TO_DTO.getCity(),
                USER_TO_DTO.getAddress(), USER_TO_DTO.getJoinedOn());
    }

    public static CreateUserDto createCreateUserDtoWithSetParameters() {
        return new CreateUserDto(USER_TO_DTO.getUsername(), USER_TO_DTO.getPassword(), USER_TO_DTO.getFirstName(),
                USER_TO_DTO.getLastName(), USER_TO_DTO.getEmail(), USER_TO_DTO.getPhoneNumber(), USER_TO_DTO.getCity(),
                USER_TO_DTO.getAddress());
    }

    public static LoginRequestDto createLoginRequestDtoWithSetParameters() {
        return new LoginRequestDto(USER_TO_DTO.getUsername(), USER_TO_DTO.getPassword());
    }

    public static UpdateUserDto createUpdateUserDtoWithSetParameters () {
        return new UpdateUserDto("updatedemail", "updatedcity", "updatedaddress", "000111");
    }
}
