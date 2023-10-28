package org.georgiev.tourwhiz.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Class<?> resourceClass, Long id) {
        super("%s with ID '%d' was not found.".formatted(resourceClass.getSimpleName(), id));
    }
}
