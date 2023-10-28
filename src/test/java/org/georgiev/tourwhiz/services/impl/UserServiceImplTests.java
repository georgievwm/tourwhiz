package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.repositories.UserRepository;
import org.georgiev.tourwhiz.exceptions.UserNotFoundException;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.factories.UserTestFactory;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.validators.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.georgiev.tourwhiz.factories.UserTestFactory.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTests {
    @Mock
    AuthenticationServiceImpl authenticationService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserValidator userValidator;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    private User updated;
    private TourWhizUserPrincipal userPrincipal;

    @BeforeEach
    void setUp() {
        user = UserTestFactory.createWithSetParameters();
        updated = UserTestFactory.createWithUpdatedParameters();

        userPrincipal = new TourWhizUserPrincipal(user.getId(), user.getUsername());
    }

    @Test
    void creatingUserSavesToRepositoryAndReturnsIt() {
        when(userRepository.save(user)).thenReturn(user);

        User registered = userService.register(user);

        assertThat(registered).isSameAs(user);
    }

    @Test
    void findingUserByIdReturnsIt() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));

        User found = userService.findById(USER_ID);

        assertThat(found).isSameAs(user);
    }

    @Test
    void updateUserUpdatesSavesInRepositoryAndReturnsIt() {
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(authenticationService.getCurrentPrincipal()).thenReturn(userPrincipal);

        User updatedUser = userService.update(USER_ID, updated);

        assertThat(updatedUser.getId()).isEqualTo(updated.getId());
        assertThat(updatedUser.getFirstName()).isEqualTo(updated.getFirstName());
        assertThat(updatedUser.getLastName()).isEqualTo(updated.getLastName());
        assertThat(updatedUser.getUsername()).isEqualTo(updated.getUsername());
        assertThat(updatedUser.getPassword()).isEqualTo(updated.getPassword());
        assertThat(updatedUser.getEmail()).isEqualTo(updated.getEmail());
        assertThat(updatedUser.getAddress()).isEqualTo(updated.getAddress());
    }

    @Test
    void registerUserWithAlreadyExistingUsernameThrowsException() {
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);

        User toRegister = UserTestFactory.createWithSetParameters();

        assertThrows(ValidationException.class, () -> userService.register(toRegister));
    }

    @Test
    void registerUserWithAlreadyExistingEmailThrowsException() {
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        User toRegister = UserTestFactory.createWithSetParameters();

        assertThrows(ValidationException.class, () -> userService.register(toRegister));
    }

    @Test
    void registerUserWithAlreadyExistingPhoneNumberThrowsException() {
        when(userRepository.existsByPhoneNumber(user.getPhoneNumber())).thenReturn(true);

        User toRegister = UserTestFactory.createWithSetParameters();

        assertThrows(ValidationException.class, () -> userService.register(toRegister));
    }

    @Test
    void loggingInWithInvalidUsernameThrowsException() {
        User attemptingLogin = createWithSetParameters();
        attemptingLogin.setUsername(NON_EXISTENT_USERNAME);

        assertThrows(UserNotFoundException.class, () ->
                userService.login(attemptingLogin));

    }

    @Test
    void loggingInWithInvalidPasswordThrowsException() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        User attemptingLogin = userService.findByUsername(user.getUsername());
        attemptingLogin.setPassword(NON_EXISTENT_PASSWORD);

        assertThrows(UserNotFoundException.class, () ->
                userService.login(attemptingLogin));
    }

    @Test
    void loggingInWithValidParametersReturnsLoggedInUser() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("password", user.getPassword())).thenReturn(true);

        User attemptingLogin = userService.findByUsername(user.getUsername());
        String currentPassword = "password";
        attemptingLogin.setPassword(currentPassword);

        User loggedIn = userService.login(attemptingLogin);

        assertThat(attemptingLogin).isSameAs(loggedIn);
    }
}
