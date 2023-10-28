package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.factories.UserTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class UserValidatorTests {
    private final UserValidator userValidator = new UserValidator();
    private User user;

    @BeforeEach
    void setUp() {
        user = UserTestFactory.createWithSetParameters();
    }

    @Test
    void validateCreateWithNullUsernameThrowsException() {
        user.setUsername(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithEmptyUsernameThrowsException() {
        user.setUsername("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithUsernameMoreThan30CharactersThrowsException() {
        user.setUsername("x".repeat(31));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithNullFirstNameThrowsException() {
        user.setFirstName(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithEmptyFirstNameThrowsException() {
        user.setFirstName("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithFirstNameMoreThan30CharactersThrowsException() {
        user.setFirstName("x".repeat(31));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithNullPasswordThrowsException() {
        user.setPassword(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithEmptyPasswordThrowsException() {
        user.setPassword("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithPasswordMoreThan20CharactersThrowsException() {
        user.setPassword("null".repeat(21));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithEmptyLastNameThrowsException() {
        user.setLastName("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithLastNameMoreThan30CharactersThrowsException() {
        user.setLastName("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithNullEmailThrowsException() {
        user.setEmail(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithNullEmailThrowsException() {
        user.setEmail(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateCreateWithBlankEmailThrowsException() {
        user.setEmail("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithBlankEmailThrowsException() {
        user.setEmail("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateCreateWithEmailMoreThan30CharactersThrowsException() {
        user.setEmail("x".repeat(31));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithEmailMoreThan30CharactersThrowsException() {
        user.setEmail("x".repeat(31));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateCreateWithPhoneNumberMoreThan20CharactersThrowsException() {
        user.setPhoneNumber("x".repeat(21));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithNullPhoneNumberThrowsException() {
        user.setPhoneNumber(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithEmptyPhoneNumberThrowsException() {
        user.setPhoneNumber("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithPhoneNumberMoreThan20CharactersThrowsException() {
        user.setPhoneNumber("x".repeat(21));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateUpdateWithNullPhoneNumberThrowsException() {
        user.setPhoneNumber(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateUpdateWithEmptyPhoneNumberThrowsException() {
        user.setPhoneNumber("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateCreateWithNullAddressThrowsException() {
        user.setAddress(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithBlankAddressThrowsException() {
        user.setAddress("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithNullAddressThrowsException() {
        user.setAddress(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateUpdateWithBlankAddressThrowsException() {
        user.setAddress("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateCreateWithNullCityThrowsException() {
        user.setCity(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithBlankCityThrowsException() {
        user.setCity("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateCreateWithCityMoreThan40CharactersThrowsException() {
        user.setCity("x".repeat(41));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnCreate(user);
        });
    }

    @Test
    void validateUpdateWithNullCityThrowsException() {
        user.setCity(null);

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateUpdateWithBlankCityThrowsException() {
        user.setCity("");

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }

    @Test
    void validateUpdateWithCityMoreThan40CharactersThrowsException() {
        user.setCity("x".repeat(41));

        assertThrows(ValidationException.class, () -> {
            userValidator.validateOnUpdate(user);
        });
    }
}
