package org.georgiev.tourwhiz.presentation.dtos;

public class UpdateVenueDto {
    private String address;
    private String city;
    private String phoneNumber;
    private String email;

    public UpdateVenueDto(String address, String city, String phoneNumber, String email) {
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UpdateVenueDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
