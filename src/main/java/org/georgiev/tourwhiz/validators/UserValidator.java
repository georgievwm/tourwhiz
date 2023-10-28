package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private void validateUsername(User user) {
        final String username = user.getUsername();

        if (username == null || username.isBlank() || username.length() > 30) {
            throw new ValidationException("The 'username' field's character length cannot exceed 30 or be equal to zero.");
        }
    }

    private void validatePassword(User user) {
        final String password = user.getPassword();

        if (password == null || password.isBlank() || password.length() > 20) {
            throw new ValidationException("The 'password' field's character length cannot exceed 20 or be equal to zero.");
        }
    }

    private void validateFirstName(User user) {
        final String firstName = user.getFirstName();

        if (firstName == null || firstName.isBlank() || firstName.length() > 30) {
            throw new ValidationException("The 'first name' field's character length cannot exceed 30 or be equal to zero.");
        }
    }

    private void validateLastName(User user) {
        final String lastName = user.getLastName();

        if (lastName.isBlank() || lastName.length() > 30) {
            throw new ValidationException("The 'last name' field's character length cannot exceed 30 or be equal to zero.");
        }
    }

    private void validateEmail(User user) {
        final String email = user.getEmail();

        if (email == null || email.isBlank() || email.length() > 30) {
            throw new ValidationException("The 'email' field's character length cannot exceed 30 or be equal to zero.");
        }
    }

    private void validatePhoneNumber(User user) {
        final String phoneNumber = user.getPhoneNumber();

        if (phoneNumber == null || phoneNumber.isBlank() || phoneNumber.length() > 20) {
            throw new ValidationException("The 'phone number' field's character length cannot exceed 20 or be equal to zero.");
        }
    }

    private void validateAddress(User user) {
        final String address = user.getAddress();

        if (address == null || address.isBlank()) {
            throw new ValidationException("The 'address' field cannot be empty.");
        }
    }

    private void validateCity(User user) {
        final String city = user.getCity();

        if (city == null || city.isBlank() || city.length() > 40) {
            throw new ValidationException("The 'city' field character length cannot exceed 40 or cannot be equal to zero.");
        }
    }

    public void validateOnCreate(User user) {
        validateUsername(user);
        validateFirstName(user);
        validateLastName(user);
        validatePassword(user);
        validateEmail(user);
        validatePhoneNumber(user);
        validateAddress(user);
        validateCity(user);
    }

    public void validateOnUpdate(User user) {
        validateEmail(user);
        validateAddress(user);
        validatePhoneNumber(user);
        validateCity(user);
    }
}
