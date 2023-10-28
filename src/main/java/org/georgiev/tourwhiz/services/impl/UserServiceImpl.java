package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.repositories.UserRepository;
import org.georgiev.tourwhiz.exceptions.ResourceNotFoundException;
import org.georgiev.tourwhiz.exceptions.UnauthorizedActionException;
import org.georgiev.tourwhiz.exceptions.UserNotFoundException;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.georgiev.tourwhiz.services.UserService;
import org.georgiev.tourwhiz.validators.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator, PasswordEncoder passwordEncoder, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
    }

    @Override
    public User register(User user) {
        userValidator.validateOnCreate(user);
        checkForExistingUniqueParameters(user);

        String password = user.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public User login(User attemptingLogin) {
        User existing = findByUsername(attemptingLogin.getUsername());

        checkIfPasswordsMatch(attemptingLogin, existing);

        return existing;
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException(User.class, userId));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()
                -> new UserNotFoundException(username));
    }

    @Override
    public User update(Long userId, User updated) {
        userValidator.validateOnUpdate(updated);

        User toUpdate = findById(userId);

        final TourWhizUserPrincipal principal = authenticationService.getCurrentPrincipal();

        if (!principal.getUsername().equals(toUpdate.getUsername())) {
            LOGGER.warn("The current User is not authorized to update these personal details as they belong to a different User.");

            throw new UnauthorizedActionException("You do not have permission to edit other users' personal details.");
        }

        toUpdate.setAddress(updated.getAddress());
        toUpdate.setCity(updated.getCity());
        toUpdate.setPhoneNumber(updated.getPhoneNumber());
        toUpdate.setEmail(updated.getEmail());

        return userRepository.save(toUpdate);
    }

    private void checkForExistingUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            LOGGER.warn("Unsuccessfully attempted to register with an already existing username.");

            throw new ValidationException("An account with this username already exists.");
        }
    }

    private void checkForExistingEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            LOGGER.warn("Unsuccessfully attempted to register with an already existing email.");

            throw new ValidationException("An account with this email already exists.");
        }
    }

    private void checkForExistingPhoneNumber(String phoneNumber) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            LOGGER.warn("Unsuccessfully attempted to register with an already existing phone number.");

            throw new ValidationException("An account with this phone number already exists.");
        }
    }

    public void checkForExistingUniqueParameters(User user) {
        checkForExistingUsername(user.getUsername());
        checkForExistingEmail(user.getEmail());
        checkForExistingPhoneNumber(user.getPhoneNumber());
    }

    private void checkIfPasswordsMatch(User attemptingLogin, User existing) {
        boolean passwordsMatch = passwordEncoder.matches(attemptingLogin.getPassword(), existing.getPassword());

        if (!passwordsMatch) {
            LOGGER.warn("Unsuccessfully attempted to login with an incorrect password.");

            throw new UserNotFoundException(attemptingLogin.getUsername());
        }
    }
}
