package org.georgiev.tourwhiz.mappers;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.presentation.dtos.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User updateDtoToEntity(UpdateUserDto updateUserDto) {
        User user = new User();
        user.setAddress(updateUserDto.getAddress());
        user.setEmail(updateUserDto.getEmail());
        user.setPhoneNumber(updateUserDto.getPhoneNumber());
        user.setCity(updateUserDto.getCity());

        return user;
    }

    public User loginDtoToEntity(LoginRequestDto loginRequestDto) {
        User user = new User();
        user.setUsername(loginRequestDto.getUsername());
        user.setPassword(loginRequestDto.getPassword());

        return user;
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhoneNumber(), user.getCity(), user.getAddress(), user.getJoinedOn());
    }

    public LoginResponseDto toLoginResponseDto(String username, String token) {
        return new LoginResponseDto(username, token);
    }

    public User createDtoToEntity(CreateUserDto createUserDto) {
        User user = new User();

        user.setUsername(createUserDto.getUsername());
        user.setPassword(createUserDto.getPassword());
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setEmail(createUserDto.getEmail());
        user.setPhoneNumber(createUserDto.getPhoneNumber());
        user.setCity(createUserDto.getCity());
        user.setAddress(createUserDto.getAddress());

        return user;
    }

    public CreateUserDto toCreateDto(User user) {
        return new CreateUserDto(user.getUsername(), user.getPassword(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getPhoneNumber(), user.getCity(), user.getAddress());
    }
}
