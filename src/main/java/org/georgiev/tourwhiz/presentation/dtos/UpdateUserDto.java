package org.georgiev.tourwhiz.presentation.dtos;

public class UpdateUserDto {

    private String email;
    private String city;
    private String address;
    private String phoneNumber;

    public UpdateUserDto() {
    }

    public UpdateUserDto(String email, String city, String address, String phoneNumber) {
        this.email = email;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
