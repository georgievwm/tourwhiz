package org.georgiev.tourwhiz.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String username) {
        super("An account with username '%s' does not exist.".formatted(username));
    }
}
