package org.georgiev.tourwhiz.presentation.dtos;

import org.georgiev.tourwhiz.data.models.VenueType;

public class VenueDto {

    private String name;
    private VenueType type;
    private String city;
    private String address;
    private String email;
    private String phoneNumber;
    private String website;

    public VenueDto(String name, VenueType type, String city, String address,
                    String email, String phoneNumber, String website) {
        this.name = name;
        this.type = type;
        this.city = city;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    public VenueDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VenueType getType() {
        return type;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
