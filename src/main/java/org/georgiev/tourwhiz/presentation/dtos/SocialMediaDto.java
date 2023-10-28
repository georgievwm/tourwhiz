package org.georgiev.tourwhiz.presentation.dtos;

import org.georgiev.tourwhiz.data.models.SocialMediaType;

public class SocialMediaDto {

    private SocialMediaType type;
    private String webpage;

    public SocialMediaDto(SocialMediaType type, String webpage) {
        this.type = type;
        this.webpage = webpage;
    }

    public SocialMediaDto() {
    }

    public SocialMediaType getType() {
        return type;
    }

    public void setType(SocialMediaType socialMediaType) {
        this.type = socialMediaType;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }
}
