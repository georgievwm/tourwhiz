package org.georgiev.tourwhiz.security;

import org.springframework.stereotype.Component;

@Component
public class TourWhizUserPrincipal {
    private Long id;
    private String username;

    public TourWhizUserPrincipal(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public TourWhizUserPrincipal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
