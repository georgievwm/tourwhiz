package org.georgiev.tourwhiz.presentation.controllers;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.mappers.UserMapper;
import org.georgiev.tourwhiz.presentation.dtos.*;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.georgiev.tourwhiz.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, UserMapper userMapper, AuthenticationService authenticationService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authenticationService = authenticationService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable Long userId) {
        final User toFind = userService.findById(userId);
        final UserDto toFindDto = userMapper.toDto(toFind);

        return ResponseEntity.ok(toFindDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        final User attemptingLogin = userMapper.loginDtoToEntity(loginRequestDto);

        final User loggedIn = userService.login(attemptingLogin);
        final String token = authenticationService.generateToken(loggedIn);

        final LoginResponseDto responseDto = userMapper.toLoginResponseDto(loggedIn.getUsername(), token);

        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto createUserDto) throws ValidationException {
        final User created = userService.register(userMapper.createDtoToEntity(createUserDto));
        final UserDto createdDto = userMapper.toDto(created);

        return ResponseEntity.ok(createdDto);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> update(@PathVariable Long userId, @RequestBody UpdateUserDto updateUserDto) {
        final User toUpdate = userMapper.updateDtoToEntity(updateUserDto);
        final User updated = userService.update(userId, toUpdate);
        final UserDto updatedDto = userMapper.toDto(updated);

        return ResponseEntity.ok(updatedDto);
    }

}
