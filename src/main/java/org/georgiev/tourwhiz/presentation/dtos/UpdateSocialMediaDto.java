package org.georgiev.tourwhiz.presentation.dtos;

public class UpdateSocialMediaDto {
    private String webpage;

    public UpdateSocialMediaDto(String webpage) {
        this.webpage = webpage;
    }

    public UpdateSocialMediaDto() {

    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}
