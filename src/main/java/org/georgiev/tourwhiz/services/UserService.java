package org.georgiev.tourwhiz.services;

import org.georgiev.tourwhiz.data.models.User;

public interface UserService {

    User register(User user);

    User login(User tryingToLogin);

    User findById(Long userId);

    User findByUsername(String username);

    User update(Long userId, User updated);

}
