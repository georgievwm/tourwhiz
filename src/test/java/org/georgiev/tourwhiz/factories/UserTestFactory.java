package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.data.models.User;

import java.time.Instant;

public class UserTestFactory {

    public static Long USER_ID = 1L;
    public static String USERNAME = "username";
    public static Long NON_EXISTENT_USER_ID = 14L;
    public static String NON_EXISTENT_USERNAME = "FALSE";
    public static String NON_EXISTENT_PASSWORD = "FALSEPASS";
    public static Review USER_REVIEW = ReviewTestFactory.createWithSetParameters();

    public static User createWithSetParameters() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("Georgi");
        user.setLastName("Georgiev");
        user.setEmail("georgievwm@gmail.com");
        user.setCity("Burgas");
        user.setAddress("Izgrev, 23bl");
        user.setPhoneNumber("0895525469");
        user.setJoinedOn(Instant.now());

        return user;
    }

    public static User createWithUpdatedParameters() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername("username");
        user.setPassword("password");
        user.setFirstName("Georgi");
        user.setLastName("Georgiev");
        user.setEmail("georgievwm222@gmail.com");
        user.setCity("Sofia");
        user.setAddress("Mladost, 485bl");
        user.setPhoneNumber("0895123123");
        user.setJoinedOn(Instant.now());
        user.addReview(USER_REVIEW);

        return user;
    }
}
