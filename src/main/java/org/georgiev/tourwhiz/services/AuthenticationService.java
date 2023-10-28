package org.georgiev.tourwhiz.services;

import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;

public interface AuthenticationService {
    String generateToken(User user);

    TourWhizUserPrincipal parseToken(String token);

    TourWhizUserPrincipal getCurrentPrincipal();
}
